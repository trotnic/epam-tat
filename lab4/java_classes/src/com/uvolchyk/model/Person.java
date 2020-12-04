package com.uvolchyk.model;

import java.util.Date;

public class Person {
    private String id;
    private String name;
    private String surname;
    private String lastname;
    private Date dateOfBirth;
    private Address address;
    private String phoneNumber;

    private Person() { }

    public Person(String id, String name, String surname, String lastname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.dateOfBirth = null;
        this.address = null;
        this.phoneNumber = null;
    }

    public Person(String id, String name, String surname, String lastname, Date dateOfBirth, Address address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class Builder {
        private Person person;

        public Builder() {
            person = new Person();
        }

        public Builder withId(String id) {
            person.setId(id);
            return this;
        }

        public Builder withName(String name) {
            person.setName(name);
            return this;
        }

        public Builder withSurname(String surname) {
            person.setSurname(surname);
            return this;
        }

        public Builder withLastname(String lastname) {
            person.setLastname(lastname);
            return this;
        }

        public Builder withDateOfBirth(Date date) {
            person.setDateOfBirth(date);
            return this;
        }

        public Builder withAddress(Address address) {
            person.setAddress(address);
            return this;
        }

        public Builder withPhoneNumber(String number) {
            person.setPhoneNumber(number);
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
