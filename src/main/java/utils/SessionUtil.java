package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static SessionUtil _Instance;
    public static SessionUtil getInstance() {
        if (_Instance == null) {
            _Instance = new SessionUtil();
        }
        return _Instance;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }

    public Object getProperties(String key, HttpSession httpSession) {
        return httpSession.getAttribute(key);
    }
}
