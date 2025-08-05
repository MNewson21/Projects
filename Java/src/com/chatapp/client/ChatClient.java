package com.chatapp.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ChatClient {

    private WebSocketClient client;
    private JTextArea chatArea;
    private JTextField inputField;
    private String username; 

    public ChatClient() {
        askForUsername();  
    }

    private void askForUsername() {
        JFrame frame = new JFrame("Enter Your Name");
        JTextField nameField = new JTextField();

        nameField.addActionListener(e -> {
            username = nameField.getText().trim();
            if (!username.isEmpty()) {
                frame.dispose(); 
                createUI();     
                connectWebSocket(); 
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Enter your name:"), BorderLayout.NORTH);
        frame.add(nameField, BorderLayout.CENTER);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUI() {
        JFrame frame = new JFrame("Chat - " + username);
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String message = inputField.getText().trim();
            if (!message.isEmpty()) {
                client.send(username + ": " + message);  // Prefix message with name
                chatArea.append("Me: " + message + "\n");
                inputField.setText("");
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void connectWebSocket() {
        try {
            client = new WebSocketClient(new URI("ws://localhost:8887")) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    chatArea.append("Connected to server\n");
                    client.send(username + " joined the chat.");
                }

                @Override
                public void onMessage(String message) {
                    chatArea.append(message + "\n");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    chatArea.append("Connection closed\n");
                }

                @Override
                public void onError(Exception ex) {
                    chatArea.append("Error: " + ex.getMessage() + "\n");
                }
            };
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
