package client_server.client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client_server.server.Server;

public class Client extends JFrame {

    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 900;
    private static final int WINDOW_POSY = 300;
    private Server server;

    private JTextField login = new JTextField("your login");
    private JPasswordField password = new JPasswordField("password");
    private JTextField ip = new JTextField("ip");
    private JTextField port = new JTextField("port");
    private JButton btnLogin = new JButton("login");
    private JButton btnStatus = new JButton("check server");

    private JTextArea textArea = new JTextArea();
    private JScrollPane scroll = new JScrollPane(textArea);

    private JTextArea msgArea = new JTextArea();
    private JButton btnSend = new JButton("Send");
    private JButton btnUpdate = new JButton("Update");

    private boolean isConnected;

    public Client(Server server) {
        this.server = server;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Client window");
        setResizable(true);
        setVisible(true);

        login.setToolTipText("Enter your login");
        password.setEchoChar('*');
        password.setToolTipText("Enter your password");
        ip.setToolTipText("Enter server ip");
        port.setToolTipText("Enter port");

        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        msgArea.setPreferredSize(new Dimension(300, 25));
        btnSend.setPreferredSize(new Dimension(70, 25));
        btnUpdate.setPreferredSize(new Dimension(100, 25));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "history");
        title.setTitleJustification(TitledBorder.RIGHT);
        title.setTitlePosition(TitledBorder.BELOW_TOP);

        JPanel logPanel = new JPanel(new GridLayout(2, 3));
        logPanel.add(login);
        logPanel.add(password);
        logPanel.add(ip);
        logPanel.add(port);
        logPanel.add(btnLogin);
        logPanel.add(btnStatus);
        add(logPanel, BorderLayout.NORTH);

        JPanel txtPanel = new JPanel(new GridLayout(1, 1));
        txtPanel.add(scroll);
        txtPanel.setBorder(title);
        add(txtPanel, BorderLayout.CENTER);

        JPanel msgPanel = new JPanel(new FlowLayout());
        msgPanel.add(msgArea);
        msgPanel.add(btnSend);
        msgPanel.add(btnUpdate);
        add(msgPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isWorking() && !isConnected) {
                    isConnected = true;
                    String cnctdMsg = System.lineSeparator()
                            + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                            + " - " + login.getText() + " connected";
                    textArea.append("\n--- previous logs -->");
                    textArea.append(System.lineSeparator());
                    textArea.append(server.sendLog());
                    textArea.append("\n<-- previous logs ---");
                    textArea.append(System.lineSeparator());
                    textArea.append(cnctdMsg);
                    server.getMsg(cnctdMsg);
                } else if (!server.isWorking()) {
                    textArea.append("\nserver is offline, try later");
                } else if (isConnected) {
                    textArea.append("\nYou are already logged in");
                }
            }
        });

        btnStatus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isWorking()) {
                    textArea.append("\nserver is online, connection is possible");
                } else {
                    textArea.append("\nserver is offline, try later");
                }
            }

        });

        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isWorking() && isConnected) {
                    updateTextField();
                }
            }
        });

        msgArea.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMsg();
                }
            }
        });
    }

    private void sendMsg() {
        if (server.isWorking() && isConnected) {
            String msg = System.lineSeparator()
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                    + " - " + login.getText() + " wrote: " + msgArea.getText();
            server.getMsg(msg);
            msgArea.setText("");
            updateTextField();
        }
    }

    private void updateTextField() {
        textArea.setText("");
        textArea.append(server.sendText());
    }
}