package com.kpi.model.services;

import com.kpi.model.entities.Enrollee;
import com.kpi.model.repositories.EnrolleeRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnrolleeService {
    private final EnrolleeRepository repository;

    public EnrolleeService(EnrolleeRepository repository) {

        this.repository = repository;
    }

    public List<Enrollee> getEnrollees() throws IOException {
        return repository.getAll();
    }

    public void setEnrollees(List<Enrollee> enrollees) throws IOException {
        this.repository.save(enrollees);
    }

    public List<Enrollee> GetEnrolleesWithBadMarks() throws IOException {
        List<Enrollee> list = getEnrollees();

        return list.stream().filter(Enrollee::hasBadMarks).collect(Collectors.toList());
    }

    public List<Enrollee> GetEnrolleesWithGPAHigherThan(double requiredScore) throws IOException {
        List<Enrollee> list = getEnrollees();

        return list.stream().filter(en -> en.getAverageScore() >= requiredScore).collect(Collectors.toList());
    }
}
