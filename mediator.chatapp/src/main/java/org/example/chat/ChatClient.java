package org.example.chat;

import java.util.List;

public interface ChatClient {
    String getUsername();
    void deliverMessage(String from, String message);
    void reflectSent(String to, String message);
    void updateUserList(List<String> usernames);
}
