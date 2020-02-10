package ui;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileSystem implements Loadable, Saveable {
    PasswordManager pm;

    public FileSystem(PasswordManager manager) {
        this.pm = manager;
    }

    public void loadUser() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Users.txt"));
        HashMap<UserInfo, Password> users = new HashMap<>();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            UserInfo u = new UserInfo(partsOfLine.get(0), partsOfLine.get(1));
            Password p = new Password();
            p.setPass(p.decode(partsOfLine.get(2)).toCharArray());
            users.put(u,p);
        }
        pm.setUsers(users);
    }

    public ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void loadAccounts(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        HashMap<AccountInfo, Password> accounts = new HashMap<>();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            AccountInfo a = new AccountInfo(partsOfLine.get(0), partsOfLine.get(1));
            Password p = new Password();
            p.setPass(p.decode(partsOfLine.get(2)).toCharArray());
            accounts.put(a, p);
        }
        pm.setAccounts(accounts);
    }


    public void saveAccounts(String file) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for (Map.Entry<AccountInfo, Password> a : pm.getAccounts().entrySet()) {
            writer.println(a.getKey().getAddress() + " " + a.getKey().getLogin() + " " + a.getValue().encode());
        }
        writer.close();
    }


    public void saveUser() throws IOException {
        PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");
        for (Map.Entry<UserInfo, Password> u : pm.getUsers().entrySet()) {
            writer.println(u.getKey().getAddress() + " " + u.getKey().getLogin() + " " + u.getValue().encode());
        }
        writer.close();
    }

//    public void createUser(String name) throws IOException {
//        System.out.println("Enter Email: ");
//        String email = scanner.nextLine();
//        Password p = new Password();
//        System.out.println("Please enter a password, enter custom for a 15 char generated password");
//        p.setPass(scanner.nextLine().toCharArray());
//        UserInfo completed = new UserInfo(email, name);
//        pm.getUsers().put(completed, p);
//        saveUser();
//        saveAccounts(name + ".txt");
//    }
}
