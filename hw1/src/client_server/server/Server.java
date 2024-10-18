package client_server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server extends JFrame {

    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 1000;
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
        setTitle("Test server");
        setResizable(true);
        isWorking = false;

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
        add(statPanel, BorderLayout.NORTH);

        setVisible(true);

    }

    protected void start() {
        if (!isWorking) {
            isWorking = true;
            textArea.append("Server is working now...\n");
            currStatus.setText("Online");
            currStatus.setForeground(Color.GREEN);

            try {
                fileWriter = new FileWriter("log.txt", true);
                fileWriter.write(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                        + " - server starts" + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {
            textArea.append("Server is already working...\n");
        }
    }

    protected void stop() {
        if (isWorking) {
            isWorking = false;
            textArea.append("Server stopped...\n");
            currStatus.setText("Offline");
            currStatus.setForeground(Color.RED);
            try {
                fileWriter.write(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                        + " - server stopped" + System.lineSeparator());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            textArea.append("Server is not working\n");
        }
    }

    public void getMsg(String message) {
        if (isWorking) {
            textArea.append(message);
            try {
                fileWriter.write(message);
            } catch (IOException e) {
                textArea.append(e.getMessage());
            }
        }
    }

}