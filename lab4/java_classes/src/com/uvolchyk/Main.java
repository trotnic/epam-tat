package com.uvolchyk;

import com.uvolchyk.model.Address;
import com.uvolchyk.model.Diagnosis;
import com.uvolchyk.model.Patient;
import com.uvolchyk.model.Person;
import com.uvolchyk.util.DateOfBirthGenerator;
import com.uvolchyk.util.Hospital;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Orlando", "Geek", "Rowan");
        List<String> surnames = Arrays.asList("Grant", "Leon", "Melvin", "David");
        List<String> lastnames = Arrays.asList("Brady", "Flynn", "White", "Edwards");
        List<Address> addresses = Arrays.asList(
                new Address("Belarus", "Minsk", "Oktabr'skaya"),
                new Address("Russia", "Moscow", "Arbat"),
                new Address("Belarus", "Brest", "Moskovskaya"),
                new Address("Belarus", "Pinsk", "Pervomayskaya")
        );
        List<String> phoneNumbers = Arrays.asList(
                "+312485239",
                "+354481426",
                "+3424853239",
                "+4122483239",
                "+02248435239"
        );
        List<Diagnosis> diagnoses = Arrays.asList(
                new Diagnosis("Fewer"),
                new Diagnosis("headache"),
                new Diagnosis("Cough")
        );

        Random random = new Random();

        List<Patient> patientList = IntStream.range(0,11).mapToObj(i -> {
            Person patient = new Person.Builder()
                    .withAddress(addresses.get(random.nextInt(addresses.size())))
                    .withDateOfBirth(DateOfBirthGenerator.generateDateBetweenBounds(1980, 2000))
                    .withId(String.valueOf(random.nextInt(100000000)))
                    .withName(names.get(random.nextInt(names.size())))
                    .withSurname(surnames.get(random.nextInt(surnames.size())))
                    .withLastname(lastnames.get(random.nextInt(lastnames.size())))
                    .withPhoneNumber(phoneNumbers.get(random.nextInt(phoneNumbers.size())))
                    .build();
            return new Patient(patient, random.nextInt(10000), diagnoses.get(random.nextInt(diagnoses.size())));
        }).collect(Collectors.toList());


        Hospital hospital = new Hospital(patientList);

        System.out.println("Patients by diagnosis");
        hospital.findPatientsWithDiagnosis(new Diagnosis("cough"))
                .forEach(System.out::println);

        System.out.println("Patients by medical card id");
        hospital.findPatientsWithIdBetweenBounds(2000, 6000)
                .forEach(System.out::println);
    }
}
