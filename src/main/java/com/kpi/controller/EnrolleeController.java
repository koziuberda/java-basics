package com.kpi.controller;

import com.kpi.model.entities.Enrollee;
import com.kpi.model.exceptions.InvalidGPAValueException;
import com.kpi.model.repositories.EnrolleeRepository;
import com.kpi.model.services.EnrolleeService;
import com.kpi.model.utilities.EnrolleeGenerator;
import com.kpi.view.EnrolleeView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EnrolleeController {
    private final EnrolleeService service;
    private final EnrolleeView view;
    private static final Logger logger = LogManager.getLogger(EnrolleeController.class);

    public EnrolleeController() {
        logger.info("Initializing the enrollee controller...");

        view = new EnrolleeView();

        File file = null;
        try {
            file = loadFileFromResources();
        } catch (IOException e) {
            view.printMessage(EnrolleeView.FAILED_TO_LOAD_FILE);
            logger.error("Unable to load the file" + e.getMessage());
            System.exit(0);
        }

        service = new EnrolleeService(new EnrolleeRepository(file));
    }

    public void start() {
        logger.info("Application started");

        while (true){
            int decision = view.getUserDecision();

            boolean shouldContinue = false;
            try {
                shouldContinue = handleUserQuery(decision);
            } catch (IOException e) {
                view.printMessage(EnrolleeView.PROBLEM_WITH_STORAGE);
            }

            if (!shouldContinue) break;
        }

        logger.info("Application finished");
    }

    private boolean handleUserQuery(int option) throws IOException {
        switch (option) {
            case 1:
                List<Enrollee> inMemoreEnrollees = EnrolleeGenerator.generateEnrollees();
                view.printMessage("We've generated 10 new enrollees!");
                view.printEnrollees(inMemoreEnrollees);
                view.printMessage("You can save them choosing appropriate option.");
                if (view.confirmSaving()){
                    service.setEnrollees(inMemoreEnrollees);
                }
                break;
            case 2:
                view.printEnrollees(service.getEnrollees());
                break;
            case 3:
                view.printEnrollees(service.GetEnrolleesWithBadMarks());
                break;
            case 4:
                double minGPA = view.getMinGPA();
                try {
                    view.printEnrollees(service.GetEnrolleesWithGPAHigherThan(minGPA));
                } catch (InvalidGPAValueException e) {
                    view.printMessage(e.getMessage());
                }
                break;
            case 5:
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
