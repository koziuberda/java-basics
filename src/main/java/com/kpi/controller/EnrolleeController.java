package com.kpi.controller;

import com.kpi.model.entities.Enrollee;
import com.kpi.model.repositories.EnrolleeFileRepository;
import com.kpi.model.services.EnrolleeService;
import com.kpi.model.utilities.EnrolleeGenerator;
import com.kpi.view.EnrolleeView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EnrolleeController {
    private List<Enrollee> inMemoreEnrollees = new ArrayList<>();
    private final EnrolleeService service;
    private final EnrolleeView view;

    public EnrolleeController() {
        view = new EnrolleeView();

        File file = null;
        try {
            file = loadFileFromResources();
        } catch (IOException e) {
            view.printMessage(EnrolleeView.FAILED_TO_LOAD_DATA);
            System.exit(0);
        }

        service = new EnrolleeService(new EnrolleeFileRepository(file));
    }

    public void start() {
        try {
            view.printEnrollees(service.getEnrollees());
        } catch (IOException e) {
            view.printMessage(EnrolleeView.PROBLEM_WITH_STORAGE);
        }

        while (true){
            int desicion = view.getUserDecision();
            boolean shouldContinue = false;
            try {
                shouldContinue = handleUserQuery(desicion);
            } catch (IOException e) {
                view.printMessage(EnrolleeView.PROBLEM_WITH_PARSING);
            }

            if (!shouldContinue) break;
        }
    }

    private boolean handleUserQuery(int option) throws IOException {
        switch (option) {
            case 1:
                inMemoreEnrollees = EnrolleeGenerator.generateEnrollees();
                break;
            case 2:
                service.setEnrollees(inMemoreEnrollees);
                break;
            case 3:
                view.printEnrollees(service.getEnrollees());
                break;
            case 4:
                view.printEnrollees(service.GetEnrolleesWithBadMarks());
                break;
            case 5:
                double minGPA = view.getMinGPA();
                view.printEnrollees(service.GetEnrolleesWithGPAHigherThan(minGPA));
                break;
            case 6:
                view.exit();
                return false;
        }

        return true;
    }

    private File loadFileFromResources() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("src/main/resources/appsettings.properties");
        properties.load(fileReader);
        return new File(properties.getProperty("storage"));
    }
}
