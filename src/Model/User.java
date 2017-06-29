package Model;

import Dictionary.UserRoleDictionary;

/**
 * Represents a registered user in the aquapark
 */
public class User {

    /**
     * id of the user
     */
    private int id;

    /**
     * user's first name
     */
    private String firstName;

    /**
     * user's last name
     */
    private String lastName;

    /**
     * user's email
     */
    private String email;

    /**
     * user's telephone number
     */
    private String telephone;

    /**
     * user's nick
     */
    private String nick;

    /**
     * user's password
     */
    private String password;

    /**
     * user's role
     */
    private UserRoleDictionary role;

    /**
     * Constructor without parametric
     * Sets fields firstName, lastName, email, telephone to null
     */
    public User() {
        this.firstName = null;
        this.lastName  = null;
        this.email = null;
        this.telephone = null;
    }

    /**
     * Constructor
     *
     * @param firstName first name of the new user
     * @param lastName last name of the new user
     * @param email email of the new user
     * @param telephone telephone of the new user
     * @param nick nick of the new user
     * @param password password of the new user
     * @param role role of the new user
     */
    public User(String firstName, String lastName, String email, String telephone, String nick, String password, UserRoleDictionary role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.nick = nick;
        this.password = password;
        this.role = role;
    }

    /**
     * Getter for id
     * @return id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id new id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for first name
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for first name
     * @param firstName new first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for last name
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for last name
     * @param lastName new last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for telephone
     * @return user's telephone number
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter for telephone
     * @param telephone new telephone of the user
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter for nick
     * @return user's nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Setter for nick
     * @param nick new nick of the user
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Getter for password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for role
     * @return user's role
     */
    public UserRoleDictionary getRole() {
        return role;
    }

    /**
     * Setter for role
     * @param role new role for the user
     */
    public void setRole(UserRoleDictionary role) {
        this.role = role;
    }

    /**
     * Stringifies user
     * @return user's nick
     */
    @Override
    public String toString() {
        return nick;
    }
}
