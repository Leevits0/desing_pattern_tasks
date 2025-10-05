package org.example.chat;

import java.util.List;

public interface ChatMediator {
    void register(ChatClient client);
    void unregister(String username);
    void sendMessage(String from, String to, String text);
    List<String> getAllUsernames();
}
