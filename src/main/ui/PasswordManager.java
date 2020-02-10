package ui;


import model.*;


import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class PasswordManager extends JDialog {
    private HashMap<AccountInfo, Password> accounts = new HashMap<>();
    private HashMap<UserInfo, Password> users = new HashMap<>();


    //EFFECTS: generates new account manager instance
    public static void main(String[] args) throws IOException {
        PasswordManager passwordManager = new PasswordManager();
        FileSystem fs = new FileSystem(passwordManager);
        fs.loadUser();
        String selectedUser = passwordManager.initializeLogin();
        fs.loadAccounts(selectedUser + ".txt");
        passwordManager.initializeManager();
        fs.saveAccounts(selectedUser + ".txt");
    }

    // MODIFIES: this
    // EFFECTS:  initiates login sequence, returns username after successful login
    public String initializeLogin() {
        String success = "fail";
        try {
            LoginUI dialog = new LoginUI(users);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            success = dialog.getUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // MODIFIES: this
    // EFFECTS:  initiates account manager sequence, returns updated accounts after all operations
    private void initializeManager() {
        HashMap<AccountInfo, Password> success;
        try {
            ManagerUI operate = new ManagerUI(accounts);
            operate.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            operate.setVisible(true);
            success = operate.getAccounts();
            setAccounts(success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public HashMap<UserInfo, Password> getUsers() {
        return this.users;
    }

    public void setUsers(HashMap<UserInfo, Password> users) {
        this.users = users;
    }

    public void setAccounts(HashMap<AccountInfo, Password> accounts) {
        this.accounts = accounts;
    }

    public HashMap<AccountInfo, Password> getAccounts() {
        return accounts;
    }


//    //MODIFIES: this
//    //EFFECTS: performs add/remove/update/show operation to list of account information
//    public void operate() {
//
//    }
//
//
//    public void changeExisting(String o) {
//        if (o.equals("Remove")) {
//            try {
//                remove();
//            } catch (CantRemoveException url) {
//                System.out.println("Given URL doesn't exist");
//            } finally {
//                scanner.nextLine();
//            }
//        } else {
//            try {
//                update();
//            } catch (CantUpdateException acc) {
//                System.out.println("Given Account doesn't exist");
//            } finally {
//                scanner.nextLine();
//            }
//        }
//    }
//
//    public void display() {
//        int status = 1;
//        for (AccountInfo a : accounts.keySet()) {
//            System.out.println(status + ") Address: " + a.getAddress());
//            System.out.println(status + ") Password: " + new String(accounts.get(a).getPass()));
//            status++;
//        }
//    }
//
//    public void add() {
//        AccountInfo adding = new AccountInfo();
//        System.out.println("Please enter the url to add");
//        while (adding.getAddress().equals("default")) {
//            try {
//                adding.setAddress(scanner.nextLine());
//            } catch (InvalidAddressException invalid) {
//                System.out.println("Valid url must start with www., Try Again");
//            }
//        }
//        System.out.println("Please enter the username/email");
//        adding.setLogin(scanner.next());
//        Password input = new Password();
//        System.out.println("Please enter the password, enter custom for a 15 char generated password");
//        scanner.nextLine();
//        input.setPass(scanner.nextLine().toCharArray());
//        accounts.put(adding, input);
//        System.out.println("Successfully added new account for " + adding.getAddress());
//        scanner.nextLine();
//    }
//
//
//    public void remove() throws CantRemoveException {
//        System.out.println("Please enter the url to remove");
//        String remove = scanner.next();
//        {
//            for (AccountInfo a : accounts.keySet()) {
//                if (a.getAddress().equals(remove)) {
//                    accounts.remove(a);
//                    System.out.println("The url " + remove + " no longer exists");
//                    return;
//                }
//            }
//            throw new CantRemoveException();
//        }
//    }
//
////    //Test method for remove
////    public void remove(String url) throws CantRemoveException {
////        for (int i = 0; i < accounts.size(); i++) {
////            if (accounts.get(i).getAddress().equals(url)) {
////                accounts.remove(accounts.get(i));
////                System.out.println("The url " + url + " no longer exists");
////                return;
////            }
////        }
////        throw new CantRemoveException();
////    }
//
//
//    public void update() throws CantUpdateException {
//        System.out.println("Please enter the url to update");
//        String change = scanner.next();
//        for (AccountInfo a : accounts.keySet()) {
//            if (a.getAddress().equals(change)) {
//                System.out.println("Enter new username");
//                a.setLogin(scanner.next());
//                Password input = new Password();
//                System.out.println("Please enter the password, enter custom for a 15 char generated password");
//                scanner.nextLine();
//                input.setPass(scanner.nextLine().toCharArray());
//                accounts.put(a, input);
//                System.out.println("Update completed");
//                return;
//            }
//        }
//        throw new CantUpdateException();
//    }


}


