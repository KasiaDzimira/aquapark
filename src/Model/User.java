package Model;

import Dictionary.UserRoleDictionary;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String nick;
    private String password;
    private UserRoleDictionary role;

    public User() {
        this.firstName = null;
        this.lastName  = null;
        this.email = null;
        this.telephone = null;
    }

    public User(String firstName, String lastName, String email, String telephone, String nick, String password, UserRoleDictionary role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.nick = nick;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleDictionary getRole() {
        return role;
    }

    public void setRole(UserRoleDictionary role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return nick;
    }
}
