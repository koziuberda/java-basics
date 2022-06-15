package com.kpi.model.utilities;

import com.kpi.model.entities.Enrollee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnrolleeGenerator {
    public static final int DEFAULT_NUMBER = 10;

    private static final String[] MALE_NAMES = new String[]{
            "Ivan", "Oleksiy", "Petro", "Fedir", "Volodymyr", "Igor", "Oleg"
    };

    private static final String[] FEMALE_NAMES = new String[]{
            "Kateryna", "Olga", "Anna", "Maria", "Yulia", "Polina", "Elena"
    };

    private static final String[] SURNAMES = new String[]{
            "Shevchenko", "Bondarenko", "Kovalenko", "Tkachenko", "Kravchenko", "Rudenko", "Honcharenko", "Chernenko", "Moroz"
    };

    private static final String[] MALE_PATRONYMICS = new String[]{
            "Petrovych", "Mykolayovych", "Volodymyrovych", "Oleksandrovych", "Serhiyovych"
    };

    private static final String[] FEMALE_PATRONYMICS = new String[]{
            "Petrivna", "Mykolayivna", "Volodymyrivna", "Oleksandrivna", "Serhiyivna"
    };

    private static final String[] ADDRESSES = new String[]{
            "Pylypa Orlyka St", "Lesi Ukrainky St", "Prorizna St",
            "Arsenal'na Square", "Kopylenka St", "Hrushevs'kogo St",
            "Khreshatyk St", "Peremohy St", "Shota Rustaveli St"
    };

    private static long getIdentificationNumber() {
        long lower = 1_000_000_000;
        long upper = 10_000_000_000L;
        return new Random().nextLong(lower, upper);
    }

    private static String getName(boolean isMale) {
        String[] source = isMale ? MALE_NAMES : FEMALE_NAMES;
        int rnd = new Random().nextInt(source.length);
        return source[rnd];
    }

    private static String getSurname() {
        String[] source = SURNAMES;
        int rnd = new Random().nextInt(source.length);
        return source[rnd];
    }

    private static String getPatronymic(boolean isMale) {
        String[] source = isMale ? MALE_PATRONYMICS : FEMALE_PATRONYMICS;
        int rnd = new Random().nextInt(source.length);
        return source[rnd];
    }

    private static List<Integer> getGrades() {
        int size = 10;
        List<Integer> grades = new ArrayList<>();

        Random rnd = new Random();

        // An enrollee has bad grades with a probability of 30%
        int coefficient = rnd.nextInt(1, 11);
        boolean hasBadGrades = coefficient > 7;

        final int minGrade = hasBadGrades ? 2 : 3;
        final int maxGrade = 6;

        for (int i = 0; i < size; i++) {
            grades.add(rnd.nextInt(minGrade, maxGrade));
        }

        return grades;
    }

    private static String getPhoneNumber() {
        String countryCode = "+380";

        Random rnd = new Random();

        String firstPart = Integer.toString(rnd.nextInt(10, 100));
        String secondPart = Integer.toString(rnd.nextInt(100, 1000));
        String thirdPart = Integer.toString(rnd.nextInt(1000, 10000));

        return countryCode + " " + firstPart + " " + secondPart + " " + thirdPart;
    }

    private static String getAddress() {
        String[] source = ADDRESSES;
        int rnd = new Random().nextInt(source.length);
        return source[rnd];
    }

    private static Enrollee getEnrollee() {
        boolean isMale = new Random().nextBoolean();

        return new Enrollee(
                getIdentificationNumber(),
                getName(isMale),
                getSurname(),
                getPatronymic(isMale),
                getAddress(),
                getPhoneNumber(),
                getGrades()
        );
    }


    public static List<Enrollee> generateEnrollees() {
        return generateEnrollees(DEFAULT_NUMBER);
    }

    public static List<Enrollee> generateEnrollees(int number) {
        List<Enrollee> enrollees = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            enrollees.add(getEnrollee());
        }

        return enrollees;
    }
}
