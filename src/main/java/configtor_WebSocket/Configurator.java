package configtor_WebSocket;

import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class Configurator extends ServerEndpointConfig.Configurator {
//    @Inject
//    private static Injector injector;
//    //     CDI Endpoint
//    @Override
//    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
//        return injector.getInstance(endpointClass);
//    }

    // getHttpSession
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        if ((SessionUtil.getInstance().getProperties(SystemConstant.ACCOUNTMODEL, httpSession) != null) &&
                (!config.getUserProperties().containsKey(SystemConstant.ACCOUNTMODEL))) {
            config.getUserProperties().put(SystemConstant.ACCOUNTMODEL,
                    SessionUtil.getInstance().getProperties(SystemConstant.ACCOUNTMODEL, httpSession));
        }
    }

}
