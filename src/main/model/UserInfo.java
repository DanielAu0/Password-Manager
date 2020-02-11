package model;

public class UserInfo extends Info {

    //MODIFIES: this
    //EFFECTS:  constructs a user with fields set to "default"
    public UserInfo() {
        super();
    }

    //MODIFIES: this
    //EFFECTS:  constructs a user with fields set to corresponding parameters
    public UserInfo(String address, String login) {
        super(address, login);
    }

}
