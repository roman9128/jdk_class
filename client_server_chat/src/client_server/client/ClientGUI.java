package client_server.client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {

    private ClientLogic clientLogic;

    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 900;
    private static final int WINDOW_POSY = 300;

    private JTextField login = new JTextField("login");
    private JPasswordField password = new JPasswordField("password");
    private JTextField ip = new JTextField("ip");
    private JTextField port = new JTextField("port");
    private JButton btnLogin = new JButton("log in");
    private JButton btnStatus = new JButton("check server");

    private JTextArea textArea = new JTextArea();
    private JScrollPane scroll = new JScrollPane(textArea);

    private JTextArea msgArea = new JTextArea();
    private JButton btnSend = new JButton("Send");

    public ClientGUI(ClientLogic clientLogic) {

        this.clientLogic = clientLogic;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        msgArea.setPreferredSize(new Dimension(400, 25));
        btnSend.setPreferredSize(new Dimension(70, 25));

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
        add(msgPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clientLogic.loginLogout();
            }
        });

        btnStatus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clientLogic.checkServer();
            }

        });

        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
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

    @Override
    public String getLogin() {
        return login.getText();
    }

    @Override
    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    @Override
    public void sendMsg() {
        clientLogic.sendMsg(msgArea.getText());
    }

    @Override
    public void messageSent() {
        msgArea.setText("");
    }

    @Override
    public void receiveMsg(String log) {
        textArea.setText("");
        textArea.append(log);
    }

    @Override
    public void userConnected(String loginMsg) {
        textArea.setText("");
        showText(loginMsg);
        btnLogin.setText("log out");
    }

    @Override
    public void userDisconnected(String logoutMsg) {
        showText(logoutMsg);
        btnLogin.setText("log in");
    }

    @Override
    public void showText(String text) {
        textArea.append(text);
    }

    public void setClientLogic(ClientLogic clientLogic) {
        this.clientLogic = clientLogic;
    }

}