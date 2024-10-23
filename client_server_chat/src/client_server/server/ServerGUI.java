package client_server.server;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView {

    private ServerLogic serverLogic;

    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 600;

    private JButton startServer = new JButton("Start server");
    private JButton stopServer = new JButton("Stop server");
    private JTextArea textArea = new JTextArea();
    private JLabel currStatus = new JLabel();

    public ServerGUI(ServerLogic serverLogic) {

        this.serverLogic = serverLogic;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Server window");
        setResizable(true);
        setVisible(true);
        textArea.setEditable(false);
        stopServer.setEnabled(false);

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverLogic.start();
            }
        });

        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverLogic.stop();
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

    }

    @Override
    public void start(String startMsg) {
        textArea.setText("");
        textArea.append("Current session messages");
        textArea.append(startMsg);
        currStatus.setText("Online");
        currStatus.setForeground(Color.GREEN);
        startServer.setEnabled(false);
        stopServer.setEnabled(true);
    }

    @Override
    public void stop(String stopMsg) {
        textArea.append(stopMsg);
        currStatus.setText("Offline");
        currStatus.setForeground(Color.RED);
        startServer.setEnabled(true);
        stopServer.setEnabled(false);
    }

    @Override
    public void showText(String text) {
        textArea.append(text);
    }
}