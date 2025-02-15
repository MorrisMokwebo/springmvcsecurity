package com.mainsteam.code.springmvcsecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_detail")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    @Size(min = 1,message = "first name should have more than one characters")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 1,message = "last name should have more than one characters")
    private String lastName;

    @NotNull
    @Email
    @Size(min = 1,message = "email should have more than one characters")
    private String email;

    @NotNull
    @Column(name = "phone_number")
    @Size(max = 10, message = "phone numbers should have 10 digits")
    private String phoneNumber;


    @NotNull
    @Size(min = 1, message = "password should have more than one value")
    private String password;

    @NotNull
    @Column(name = "confirmed_password")
    @Size(min = 1, message = "password should have more than one value")
    private String confirmPassword;

    @Column(name = "verification_code",length = 64)
    private String verificationCode;

    private boolean enabled;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String phoneNumber,
                String password, String confirmPassword, String verificationCode, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName(){
        return getFirstName()+" "+getLastName();
    }


    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
