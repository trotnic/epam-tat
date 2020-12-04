package com.uvolchyk.util;

import com.uvolchyk.model.Diagnosis;
import com.uvolchyk.model.Patient;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Hospital {
    private List<Patient> patientList;

    public Hospital() {
        this.patientList = new LinkedList<>();
    }

    public Hospital(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }

    public void removePatient(Patient patient) {
        this.patientList.remove(patient);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "patientList=" + patientList +
                '}';
    }

    public List<Patient> findPatientsWithDiagnosis(Diagnosis diagnosis) {
        return this.patientList.stream()
                .filter(p -> p.getDiagnosis().equals(diagnosis))
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsWithIdBetweenBounds(Integer bottomBound, Integer upperBound) {
        return this.patientList.stream()
                .filter(p -> p.getMedCardId() >= bottomBound)
                .filter(p -> p.getMedCardId() <= upperBound)
                .collect(Collectors.toList());
    }
}
