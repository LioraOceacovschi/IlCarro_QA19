package models;

public class User {
    String name;
    String lastname;
    String email;
    String password;



    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withLastname(String lastname) {
        this.lastname = lastname;
        return this;

    }

    public User withEmail(String email) {
        this.email = email;
        return this;

    }

    public User withPassword(String password) {
        this.password = password;
        return this;

    }
}
