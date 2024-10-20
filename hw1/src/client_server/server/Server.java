package client_server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server extends JFrame {

    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 600;

    private FileWriter fileWriter;

    private JButton startServer = new JButton("Start server");
    private JButton stopServer = new JButton("Stop server");
    private JTextArea textArea = new JTextArea();
    private JLabel currStatus = new JLabel();
    private boolean isWorking;

    public Server() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Server window");
        setResizable(true);
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
            textArea.append(startMsg);
            currStatus.setText("Online");
            currStatus.setForeground(Color.GREEN);

            try {
                fileWriter = new FileWriter("log.txt", true);
                fileWriter.write(startMsg);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
            try {
                fileWriter.write(stopMsg);
                fileWriter.close();
            } catch (IOException e) {
                textArea.append(e.getMessage());
            }
            startServer.setEnabled(true);
            stopServer.setEnabled(false);
        } else {
            textArea.append("\nServer is not working");
        }
    }

    public void takeMsg(String message) {
        if (isWorking) {
            textArea.append(message);
            try {
                fileWriter.write(message);
            } catch (IOException e) {
                textArea.append(e.getMessage());
            }
        }
    }

    public String sendText() {
        return textArea.getText();
    }

    public String sendLog() {
        if (isWorking) {
            StringBuilder builder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.lineSeparator());
                }
                builder.append("___previous logs___\n");
                return builder.toString();
            } catch (Exception e) {
                textArea.append(e.getMessage());
            }
        }
        return "";
    }

    public boolean isWorking() {
        return isWorking;
    }

    public boolean checkPassword(char[] pass) {
        String okPass = "pass";
        return okPass.equals(String.valueOf(pass));
    }
}