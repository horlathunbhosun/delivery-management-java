package org.olatunbosun.session;

import java.util.*;

public class SessionManager {
    private static final Map<String, SessionData> sessions = new HashMap<>();

    public static void createSession(String email, SessionData sessionData) {
        sessions.put(email, sessionData);
    }

    public static SessionData getSession(String email) {
        return sessions.get(email);
    }

    public static void removeSession(String email) {
        sessions.remove(email);
    }
}
