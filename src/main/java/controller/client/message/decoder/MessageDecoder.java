package controller.client.message.decoder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<JsonObject> {
    private static final Gson gson = new Gson();

    @Override
    public JsonObject decode(String s) throws DecodeException {
        return gson.fromJson(s, JsonObject.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
