package model;

import java.io.IOException;

public interface Saveable {

    void saveUser() throws IOException;

    void saveAccounts(String file) throws IOException;

}
