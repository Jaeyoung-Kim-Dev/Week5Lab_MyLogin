package models;

import java.util.ArrayList;

public class AccountService {

    private final User userAbe = new User("abe", "password");
    private final User userBarb = new User("barb", "password");

    public User login(String username, String password) {
        ArrayList<User> users = new ArrayList<>();
        users.add(userAbe);
        users.add(userBarb);

        User user = new User(username, password);

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername()) && users.get(i).getPassword().equals(user.getPassword())) {
                user.setPassword(null);
                return user;
            }
        }

        return null;
    }
}
