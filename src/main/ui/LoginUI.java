package ui;

import model.Password;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class LoginUI extends JDialog {
    private JPanel panel = new JPanel();
    private JTextField username;
    private JPasswordField password;
    private HashMap<UserInfo, Password> users;


    public LoginUI(HashMap<UserInfo, Password> users) {
        setModal(true);
        this.users = users;
        setSize(450, 300);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("UserName:");
        lblUsername.setBounds(89, 76, 63, 20);
        panel.add(lblUsername);


        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(89, 119, 63, 20);
        panel.add(lblPassword);


        username = new JTextField();
        username.setBounds(173, 76, 152, 20);
        panel.add(username);
        username.setColumns(10);

        password = new JPasswordField();
        password.setBounds(173, 119, 152, 20);
        panel.add(password);

        JButton btnLogin = loginButton();
        panel.add(btnLogin);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Open Sans", Font.PLAIN, 20));
        lblLogin.setBounds(104, 23, 230, 23);
        panel.add(lblLogin);
        panel.setBackground(Color.LIGHT_GRAY);
    }

    private JButton loginButton() {
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (successfulLogin()) {
                    JOptionPane.showMessageDialog(null, "Login Successful ");
                    dispose();
                } else {
                    String msg = "Wrong username or password.";
                    JOptionPane.showMessageDialog(null, msg, "Please Check", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnLogin.setBounds(205, 165, 89, 23);
        return btnLogin;
    }

    private boolean successfulLogin() {
        for (UserInfo u : users.keySet()) {
            if (u.getLogin().equals(username.getText())) {
                return checkPassword(u);
            }
        }
        return false;
    }

    private boolean checkPassword(UserInfo u) {
        while (true) {
            char[] pass = password.getText().toCharArray();
            if (Arrays.equals(users.get(u).getPass(), pass)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String getUsername() {
        return username.getText();
    }
}
