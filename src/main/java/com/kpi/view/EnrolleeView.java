package com.kpi.view;

import com.kpi.model.entities.Enrollee;

import java.util.List;
import java.util.Scanner;

public class EnrolleeView {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String LIST_HEADER = """
            +-----------------+-----------------+-----------------+----------------------+--------------------+-----------------------+--------------------------------+
            +       Name      |      Surname    |    Patronymic   |        Address       |     Phone number   | Identification number |             Grades             |
            +-----------------+-----------------+-----------------+----------------------+--------------------+-----------------------+--------------------------------+""";
    public static final String LIST_FOOTER =
            "+-----------------+-----------------+-----------------+----------------------+--------------------+-----------------------+--------------------------------+";
    public static final String EMPTY_LIST = "Your list is empty!";
    public static final String FAILED_TO_LOAD_DATA = "Failed to load data.";
    public static final String PROBLEM_WITH_STORAGE = "Can't load the storage";
    public static final String PROBLEM_WITH_PARSING = "Can't parse the values";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printEnrollees(List<Enrollee> enrollees) {
        if(enrollees == null || enrollees.isEmpty()) {
            printMessage(EMPTY_LIST);
        } else {
            printMessage(LIST_HEADER);
            enrollees.forEach(System.out::println);
            printMessage(LIST_FOOTER);
        }
    }

    public int getUserDecision() {
        printMessage("What do you want to do?");
        printAvailableOptions();
        return getIntegerInput();
    }

    private void printAvailableOptions() {
        printMessage("Enter [1] to generate new 10 enrollees");
        printMessage("Enter [2] to save data");
        printMessage("Enter [3] to print enrollees");
        printMessage("Enter [4] to get enrollees with bad grades");
        printMessage("Enter [5] to get enrollees with min. GPA");
        printMessage("Enter [6] to exit with saving data");
    }

    public double getMinGPA() {
        printMessage("Enter please min average score:");
        while(!SCANNER.hasNextDouble()) {
            printMessage("The score should be double. Please, enter correct value.");
            SCANNER.next();
        }
        return SCANNER.nextDouble();
    }

    private int getIntegerInput() {
        while(!SCANNER.hasNextInt()) {
            printMessage("An integer value is required. Please, try again.");
            SCANNER.next();
        }
        return SCANNER.nextInt();
    }

    public void exit() {
        System.out.println("Finishing...");
    }
}
