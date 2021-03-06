package utils;

import bean.PostModel;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import constant.GoogleAPIConstant;
import org.apache.commons.fileupload.FileItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UploadFileUtil {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static Drive _driverService = null;

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException, GeneralSecurityException {
        // Load client secrets.
        // InputStream in =
        // UploadFileUtil.class.getResourceAsStream(GoogleAPIConstant.CLIENT_SECRET_FILE_NAME);
        // if (in == null) {
        // throw new FileNotFoundException("Resource not found: " +
        // GoogleAPIConstant.CLIENT_SECRET_FILE_NAME);
        // }
        // GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
        // new InputStreamReader(in));
        //
        // // Build flow and trigger user authorization request.
        // GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        // HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        // .setDataStoreFactory(new FileDataStoreFactory(new
        // java.io.File(GoogleAPIConstant.TOKENS_DIRECTORY_PATH)))
        // .setAccessType("offline")
        // .build();
        //// LocalServerReceiver receiver = new
        // LocalServerReceiver.Builder().setPort(8888).build();
        // LocalServerReceiver receiver = new
        // LocalServerReceiver.Builder().setPort(8080).build();
        // return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        Collection<String> elenco = new ArrayList<String>();
        elenco.add("https://www.googleapis.com/auth/drive");
        return new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId("thumb-nail-pbl3@managementmotel-2021.iam.gserviceaccount.com")
                .setServiceAccountScopes(elenco)
                .setServiceAccountPrivateKeyFromP12File(new java.io.File(GoogleAPIConstant.pathServiceAccountKey))
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        // LocalServerReceiver receiver = new
        // LocalServerReceiver.Builder().setPort(8080).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static File createGoogleFolder(String folderIdParent, String folderName) throws IOException {

        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();

        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        if (folderIdParent != null) {
            List<String> parents = Arrays.asList(folderIdParent);
            fileMetadata.setParents(parents);
        }

        return getInstanceDriverService().files().create(fileMetadata).setFields("id, name").execute();
    }

    // Build singleton a authorized API client service.
    private static Drive getInstanceDriverService() throws IOException {
        if (_driverService == null) {
            try {
                final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                final Credential credential = getCredentials(HTTP_TRANSPORT);
                _driverService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setHttpRequestInitializer(new HttpRequestInitializer() {
                            @Override
                            public void initialize(HttpRequest httpRequest) throws IOException {
                                credential.initialize(httpRequest);
                                httpRequest.setConnectTimeout(3 * 60000); // 300 minutes connect timeout
                                httpRequest.setReadTimeout(3 * 60000); // 300 minutes read timeout
                            }
                        })
                        .setApplicationName(GoogleAPIConstant.APPLICATION_NAME)
                        .build();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        return _driverService;
    }

    public static String uploadFile(String postId, List<FileItem> listImages)
            throws GeneralSecurityException, IOException {

        // create a root folder
        File folder = createGoogleFolder(GoogleAPIConstant.folderRootId, postId);
        for (FileItem item : listImages) {
            InputStreamContent uploadStreamContent = new InputStreamContent(
                    item.getContentType(), item.getInputStream());
            uploadFileItem(uploadStreamContent, item.getName(), folder.getId());
        }
        return folder.getId();
    }

    private static String uploadFileItem(InputStreamContent uploadStreamContent, String customFileName,
            String parents) throws IOException {
        File newGGDriveFile = new File();
        newGGDriveFile.setParents(Collections.singletonList(parents)).setName(customFileName);
        File file = getInstanceDriverService().files().create(newGGDriveFile, uploadStreamContent)
                .setFields("id,webViewLink").execute();
        return file.getWebViewLink();
    }

    public static void getListLinkOneImagesByFolderId(List<PostModel> postModels) throws IOException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (PostModel postModel : postModels) {
            es.execute(new Thread(new GetImageId(postModel, getInstanceDriverService())));
        }
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getLinkImagesByFolderId(String folderId) throws IOException {
        String query = "mimeType != 'application/vnd.google-apps.folder'"
                + " and '" + folderId + "' in parents";
        FileList result = getInstanceDriverService().files().list().setQ(query).setSpaces("drive")
                .setFields("nextPageToken, files(id, webViewLink)")
                .execute();
        List<File> files = result.getFiles();
        List<String> listFile = new ArrayList<>();
        for (File file : files) {
            listFile.add(file.getId());
        }
        return listFile.isEmpty() ? null : listFile;
    }

    public static void deleteFile(String fileId) {
        try {
            getInstanceDriverService().files().delete(fileId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFileByFolderId(String folderId, List<FileItem> listImages) throws IOException {
        for (FileItem item : listImages) {
            InputStreamContent uploadStreamContent = new InputStreamContent(
                    item.getContentType(), item.getInputStream());
            uploadFileItem(uploadStreamContent, item.getName(), folderId);
        }
    }

}

class GetImageId implements Runnable {
    private Drive _driver = null;
    private PostModel postModel = null;

    public GetImageId(PostModel postModel, Drive drive) {
        this.postModel = postModel;
        this._driver = drive;
    }

    @Override
    public void run() {
        synchronized (postModel) {
            try {
                String query = "mimeType != 'application/vnd.google-apps.folder'"
                        + " and '" + postModel.getLinkImages() + "' in parents";
                FileList result = null;
                result = this._driver.files().list().setQ(query).setSpaces("drive")
                        .setFields("nextPageToken, files(id, webViewLink)")
                        .setPageSize(1)
                        .execute();
                List<File> files = result.getFiles();
                if (!files.isEmpty())
                    postModel.setLinkImages(files.get(0).getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
