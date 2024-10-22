package client_server.server;

import javax.swing.*;

import client_server.repository.LogMaster;
import client_server.client.ClientGUI;
import client_server.client.ClientLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ServerGUI extends JFrame implements ServerView {

    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 600;

    private JButton startServer = new JButton("Start server");
    private JButton stopServer = new JButton("Stop server");
    private JTextArea textArea = new JTextArea();
    private JLabel currStatus = new JLabel();
    private boolean isWorking;
    private LogMaster logger;
    private ArrayList<ClientLogic> connectedUsers;

    public ServerGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Server window");
        setResizable(true);
        logger = new LogMaster();
        connectedUsers = new ArrayList<>();
        isWorking = false;
        textArea.setEditable(false);
        stopServer.setEnabled(false);

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(startServer);
        btnPanel.add(stopServer);
        add(btnPanel, BorderLayout.SOUTH);

        JPanel textPanel = new JPanel(new GridLayout(1, 1));
        textPanel.add(textArea);
        add(textPanel, BorderLayout.CENTER);

        JPanel statPanel = new JPanel(new GridLayout(1, 1));
        statPanel.setBackground(Color.LIGHT_GRAY);
        statPanel.add(currStatus);
        currStatus.setText("Offline");
        currStatus.setForeground(Color.RED);
        add(statPanel, BorderLayout.NORTH);

        setVisible(true);

    }

    protected void start() {
        if (!isWorking) {
            isWorking = true;
            String startMsg = System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - server starts";
            textArea.setText("");
            textArea.append("Current session messages");
            textArea.append(startMsg);
            writeToLog(startMsg);
            currStatus.setText("Online");
            currStatus.setForeground(Color.GREEN);
            startServer.setEnabled(false);
            stopServer.setEnabled(true);
        } else {
            textArea.append("\nServer is already working...");
        }
    }

    protected void stop() {
        if (isWorking) {
            isWorking = false;
            String stopMsg = System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - server stopped";
            textArea.append(stopMsg);
            currStatus.setText("Offline");
            currStatus.setForeground(Color.RED);
            writeToLog(stopMsg);
            startServer.setEnabled(true);
            stopServer.setEnabled(false);
        } else {
            textArea.append("\nServer is not working");
        }
    }

    public void addNewUser(ClientLogic client) {
        connectedUsers.add(client);
    }

    public void removeUser(ClientLogic client) {
        connectedUsers.remove(client);
    }

    public void sendNewTextToEveryone() {
        for (ClientLogic client : connectedUsers) {
            client.receiveMsg(sendLog());
        }
    }

    public void takeMsg(String message) {
        if (isWorking) {
            textArea.append(message);
            writeToLog(message);
        }
    }

    public void writeToLog(String text) {
        try {
            logger.writeToLog(text);
        } catch (IOException e) {
            textArea.append(e.getMessage());
        }
    }

    public String sendLog() {
        if (isWorking) {
            try {
                return logger.sendLog();
            } catch (FileNotFoundException e) {
                textArea.append(e.getMessage());
            } catch (IOException e) {
                textArea.append(e.getMessage());
            }
        }
        return "";
    }

    public boolean isWorking() {
        return isWorking;
    }

    public boolean checkPassword(String pass) {
        String okPass = "pass";
        return okPass.equals(pass);
    }
}