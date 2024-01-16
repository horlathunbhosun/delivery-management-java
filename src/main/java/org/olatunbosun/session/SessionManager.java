package org.olatunbosun.session;

import java.util.*;

public class SessionManager {
    private static final Map<String, SessionData> sessions = new HashMap<>();

    public static void createSession(String dataStored, SessionData sessionData) {
        sessions.put(dataStored, sessionData);
    }

    public static SessionData getSession(String dataStored){
        return sessions.get(dataStored);
    }

    public static void removeSession(String dataStored) {
        sessions.remove(dataStored);
    }
}
