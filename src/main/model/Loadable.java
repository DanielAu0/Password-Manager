package model;

import java.io.IOException;

public interface Loadable {

    void loadAccounts(String file) throws IOException;

    void loadUser() throws IOException;
}
