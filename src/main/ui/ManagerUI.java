package ui;


import model.AccountInfo;
import model.Password;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


public class ManagerUI extends JDialog {
    private HashMap<AccountInfo, Password> accounts;
    private JPanel frame = new JPanel();
    JTextField textAddress = new JTextField();
    JTextField textUser = new JTextField();
    JTextField textPassword = new JTextField();


    public ManagerUI(HashMap<AccountInfo, Password> accounts) {
        setModal(true);
        this.accounts = accounts;
        setSize(900, 400);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(frame, BorderLayout.CENTER);
        frame.setLayout(null);

        //setup JTable
        JTable table = new JTable();
        Object[] columns = {"Address", "User", "Password"};
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        Font font = new Font("Open Sans", Font.PLAIN, 15);
        table.setFont(font);
        table.setRowHeight(15);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 900, 200);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                textAddress.setText(model.getValueAt(i, 0).toString());
                textUser.setText(model.getValueAt(i, 1).toString());
                textPassword.setText(model.getValueAt(i, 2).toString());
            }
        });


        //setup user input fields
        SetUpInputs set = new SetUpInputs().invoke();
        JLabel lblAddress = set.getLblAddress();
        JLabel lblUser = set.getLblUser();
        JLabel lblPassword = set.getLblPassword();

        //setup operators/buttons
        Object[] row = new Object[3];
        loadToTable(accounts, model, row);
        JButton generateBtn = generateBtn();
        JButton addBtn = addButton(accounts, model, row);
        JButton removeBtn = removeButton(accounts, table, model);
        JButton updateBtn = updateButton(table, model, removeBtn);
        JButton quitBtn = quitButton();

        //add objects to frame
        frame.add(lblAddress);
        frame.add(lblUser);
        frame.add(lblPassword);
        frame.add(textAddress);
        frame.add(textUser);
        frame.add(textPassword);
        frame.add(generateBtn);
        frame.add(addBtn);
        frame.add(removeBtn);
        frame.add(updateBtn);
        frame.add(quitBtn);
        frame.add(pane);
        frame.setBackground(Color.LIGHT_GRAY);



    }

    private JButton generateBtn() {
        JButton generateBtn = new JButton("Generate Password");
        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Password insert = new Password();
                textPassword.setText(insert.passwordGenerator(15));
            }
        });
        generateBtn.setBounds(85, 330, 160, 25);
        return generateBtn;
    }

    private JButton quitButton() {
        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        quitBtn.setBounds(750, 260, 100, 50);
        return quitBtn;
    }

    private JButton updateButton(JTable table, DefaultTableModel model, JButton removeBtn) {
        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(600, 260, 100, 50);
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    model.setValueAt(textAddress.getText(), i, 0);
                    model.setValueAt(textUser.getText(), i, 0);
                    model.setValueAt(textPassword.getText(), i, 0);

//                    for (AccountInfo a : accounts.keySet()) {
//                        if (a.getAddress().equals(table.getValueAt(i, 0))) {
//                            try {
//                                a.setAddress(textAddress.getText());
//                            } catch (InvalidAddressException ex) {
//                                JOptionPane.showMessageDialog();
//                                return;
//                            }
//                            a.setLogin(textUser.getText());
//                            Password input = new Password(textPassword.getText());
//                            accounts.put(a, input);
//                        }
//                    }
                }
            }
        });
        return updateBtn;
    }

    private JButton removeButton(HashMap<AccountInfo, Password> accounts, JTable table, DefaultTableModel model) {
        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(450, 260, 100, 50);
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                    for (AccountInfo a : accounts.keySet()) {
                        if (a.addressMatch(textAddress.getText())) {
                            accounts.remove(a);
                            return;
                        }
                    }
                }
            }
        });
        return removeBtn;
    }

    private JButton addButton(HashMap<AccountInfo, Password> accounts, DefaultTableModel model, Object[] row) {
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(300, 260, 100, 50);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountInfo adding = new AccountInfo(textAddress.getText(), textUser.getText());
                Password password = new Password(textPassword.getText());
                accounts.put(adding, password);

                row[0] = adding.getAddress();
                row[1] = adding.getLogin();
                row[2] = textPassword.getText();

                model.addRow(row);

            }
        });
        return addBtn;
    }

    private void loadToTable(HashMap<AccountInfo, Password> accounts, DefaultTableModel model, Object[] row) {
        for (Map.Entry<AccountInfo, Password> entry : accounts.entrySet()) {
            row[0] = entry.getKey().getAddress();
            row[1] = entry.getKey().getLogin();
            row[2] = new String(entry.getValue().getPass());

            model.addRow(row);
        }
    }


    public HashMap<AccountInfo, Password> getAccounts() {
        return accounts;
    }

    private class SetUpInputs {
        private JLabel lblAddress;
        private JLabel lblUser;
        private JLabel lblPassword;

        public JLabel getLblAddress() {
            return lblAddress;
        }

        public JLabel getLblUser() {
            return lblUser;
        }

        public JLabel getLblPassword() {
            return lblPassword;
        }

        public SetUpInputs invoke() {
            lblAddress = new JLabel("Address: ");
            lblAddress.setFont(new Font("Open Sans", Font.PLAIN, 15));
            lblAddress.setBounds(10, 235, 230, 23);
            lblUser = new JLabel("Username: ");
            lblUser.setFont(new Font("Open Sans", Font.PLAIN, 15));
            lblUser.setBounds(10, 265, 230, 23);
            lblPassword = new JLabel("Password: ");
            lblPassword.setFont(new Font("Open Sans", Font.PLAIN, 15));
            lblPassword.setBounds(10, 295, 230, 23);
            textAddress.setBounds(90, 235, 150, 25);
            textUser.setBounds(90, 265, 150, 25);
            textPassword.setBounds(90, 295, 150, 25);
            return this;
        }
    }
}
