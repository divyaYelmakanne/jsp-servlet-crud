package net.javaguides.usermanagement.model;

/**
 * User.java
 * This is a model class that represents a User entity.
 */
public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String country;

    // Empty constructor
    public User() {
    }

    // Constructor without ID
    public User(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    // Constructor with ID
    public User(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    // Get ID
    public int getId() {
        return id;
    }

    // Set ID
    public void setId(int id) {
        this.id = id;
    }

    // Get Name
    public String getName() {
        return name;
    }

    // Set Name
    public void setName(String name) {
        this.name = name;
    }

    // Get Email
    public String getEmail() {
        return email;
    }

    // Set Email
    public void setEmail(String email) {
        this.email = email;
    }

    // Get Country
    public String getCountry() {
        return country;
    }

    // Set Country
    public void setCountry(String country) {
        this.country = country;
    }
}