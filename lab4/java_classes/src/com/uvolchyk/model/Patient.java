package com.uvolchyk.model;

public class Patient extends Person {
    private Integer medCardId;
    private Diagnosis diagnosis;

    public Patient(String id, String name, String surname, String lastname, Integer medCardId) {
        super(id, name, surname, lastname);
        this.medCardId = medCardId;
        this.diagnosis = null;
    }

    public Patient(Person person, Integer medCardId, Diagnosis diagnosis) {
        super(person.getId(), person.getName(), person.getSurname(), person.getLastname(), person.getDateOfBirth(), person.getAddress(), person.getPhoneNumber());
        this.medCardId = medCardId;
        this.diagnosis = diagnosis;
    }

    public Integer getMedCardId() {
        return medCardId;
    }

    public void setMedCardId(Integer medCardId) {
        this.medCardId = medCardId;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "medCardId=" + medCardId +
                ", diagnosis=" + diagnosis +
                ", " + super.toString() +
                '}';
    }
}
