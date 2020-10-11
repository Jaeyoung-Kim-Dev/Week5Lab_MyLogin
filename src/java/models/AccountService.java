package models;

public class AccountService {
    
    User userAbe = new User("abe", "password");
    User userBarb = new User("barb", "password");
    
    public User login(String username, String password) {
        User user = new User(username, password);
        
        if (user.equals(userAbe) || user.equals(userBarb))
            return user;
        else
            return  null;
    }
}
