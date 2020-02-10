package model;

import exceptions.InvalidAddressException;

public class AccountInfo extends Info {

    //MODIFIES: this
    //EFFECTS:  constructs an account with all fields set to "default"
    public AccountInfo() {
        super();
    }

    //MODIFIES: this
    //EFFECTS:  constructs an account with fields set to corresponding parameters
    public AccountInfo(String address, String login) {
        super(address, login);
    }

    //MODIFIES: this
    //EFFECTS:  sets account url to parameter if string greater than 4 char a starts with "www."
    @Override
    public void setAddress(String url) throws InvalidAddressException {
        if (url.length() > 4) {
            if (url.substring(0, 4).equals("www.")) {
                this.address = url;
            } else {
                throw new InvalidAddressException();
            }
        } else {
            throw new InvalidAddressException();
        }
    }

}
