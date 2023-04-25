package com.fitnessapp.user;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private int experience;
    private boolean guest;

    public User(String firstName, String lastName, String email, int age, String gender, double weight, double height, int experience, boolean guest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.experience = experience;
        this.guest = guest;
    }

    public User(){
        this.firstName = "Гость";
        this.lastName = null;
        this.email = null;
        this.age = 0;
        this.gender = null;
        this.weight = 0;
        this.height = 0;
        this.experience = 0;
        this.guest = true;
    }


    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        age = in.readInt();
        gender = in.readString();
        weight = in.readDouble();
        height = in.readDouble();
        experience = in.readInt();
        guest = in.readInt() == 1;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isGuest() { return guest; }

    public void setGuest(boolean guest) { this.guest = guest; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeInt(age);
        dest.writeString(gender);
        dest.writeDouble(weight);
        dest.writeDouble(height);
        dest.writeInt(experience);
        dest.writeInt(guest ? 1 : 0);
    }

}