package model;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class Password {
    private char[] pass;

    //EFFECTS: returns the base64 encoded version of pass
    public String encode() {
        byte[] authBytes = new String(pass).getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.getEncoder().encodeToString(authBytes);
        return encoded;
    }

    //EFFECTS: takes a string that is base 64 encoded and decodes to string
    public String decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
        return decoded;
    }


    //EFFECTS:  returns a random string of parameter int length (allowed = A...Z, 0...9, a...z)
    public String passwordGenerator(int chars) {
        final String bank = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(chars);
        for (int i = 0; i < chars; i++) {
            sb.append(bank.charAt(rnd.nextInt(bank.length())));
        }
        System.out.println("Your password is: " + sb.toString());
        return sb.toString();
    }

    //EFFECTS:  returns true if given string contains a capital letter & lowercase letter & number
    public boolean strongPassword(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0;i < str.length();i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag) {
                return true;
            }
        }
        return false;
    }


    public Password() {
        this.pass = "default".toCharArray();
    }

    public Password(String input) {
        this.pass = input.toCharArray();
    }

    public char[] getPass() {
        return pass;
    }

    // MODIFIES: this
    // EFFECTS:  sets pass to parameter, if "custom", generate at 15 letter password
    public void setPass(char[] pass) {
        if (new String(pass).equals("custom")) {
            this.pass = passwordGenerator(15).toCharArray();
        } else {
            this.pass = pass;
        }
    }
}
