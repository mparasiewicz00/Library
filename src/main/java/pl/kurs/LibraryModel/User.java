package pl.kurs.LibraryModel;

import java.util.Objects;

public class User {
    private final String libraryDomain = "@mylibrary.pl";
    private long nextId;
    private long userId;
    private String firstName;
    private String lastName;
    private String userEmail;

    public User(String firstName, String lastName) {
        this.userId = ++nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = (firstName.concat(lastName).concat(libraryDomain)).toLowerCase();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userId, firstName, lastName, userEmail);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
