package org.example.chat;

import java.util.*;

public class ChatMediatorImpl implements ChatMediator {

    private final Map<String, ChatClient> clients = new LinkedHashMap<>();

    @Override
    public synchronized void register(ChatClient client) {
        clients.put(client.getUsername(), client);
        broadcastRoster();
    }

    @Override
    public synchronized void unregister(String username) {
        clients.remove(username);
        broadcastRoster();
    }

    @Override
    public synchronized void sendMessage(String from, String to, String text) {
        ChatClient sender = clients.get(from);
        ChatClient recipient = clients.get(to);

        if (sender == null) return;
        sender.reflectSent(to, text);

        if (recipient == null) {
            sender.deliverMessage("SYSTEM", "User '" + to + "' is not available.");
            return;
        }
        recipient.deliverMessage(from, text);
    }

    @Override
    public synchronized List<String> getAllUsernames() {
        return new ArrayList<>(clients.keySet());
    }

    private void broadcastRoster() {
        List<String> list = getAllUsernames();
        clients.values().forEach(c -> c.updateUserList(list));
    }
}
