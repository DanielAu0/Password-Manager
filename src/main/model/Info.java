package model;

import exceptions.InvalidAddressException;

import java.util.ArrayList;
import java.util.List;

public abstract class Info {
    protected String address;
    protected String login;

    //MODIFIES: this
    //EFFECTS:  constructs an account with all fields set to "default"
    protected Info() {
        this.address = "default";
        this.login = "default";
    }

    //MODIFIES: this
    //EFFECTS:  constructs an account with all fields set to corresponding parameters
    protected Info(String address, String login) {
        this.address = address;
        this.login = login;

    }

    //EFFECTS:  returns true if address matches parameter, false otherwise
    public boolean addressMatch(String otherAddress) {
        return this.address.equals(otherAddress);

    }


    //EFFECTS: returns true if address had as valid domain suffix, false otherwise
    public boolean validAddress() throws InvalidAddressException {
        List<String> validSuffix = new ArrayList<>();
        addValid(validSuffix);
        String suffix1 = address.substring(address.length() - 3);
        String suffix2 = address.substring(address.length() - 4);
        if (address.length() > 4) {
            if (validSuffix.contains(suffix1) || validSuffix.contains(suffix2)) {
                return true;
            } else {
                throw new InvalidAddressException();
            }
        } else {
            throw new InvalidAddressException();
        }

    }

    //EFFECTS: helper list of valid domain suffix
    private void addValid(List<String> validSuffix) {
        validSuffix.add(".com");
        validSuffix.add(".net");
        validSuffix.add(".org");
        validSuffix.add(".co");
        validSuffix.add(".us");
        validSuffix.add(".ca");
        validSuffix.add(".int");
        validSuffix.add(".edu");
        validSuffix.add(".gov");
        validSuffix.add(".io");
        validSuffix.add(".app");
    }

    //EFFECTS: returns a formatted string of the info
    @Override
    public String toString() {
        String formatted = "Address: " + address + "\n" + "Login: " + login;
        return formatted;
    }

    public void setAddress(String address) throws InvalidAddressException {
        this.address = address;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return this.address;
    }

    public String getLogin() {
        return this.login;
    }


}
