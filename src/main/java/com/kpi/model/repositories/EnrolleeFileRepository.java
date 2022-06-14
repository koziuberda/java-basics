package com.kpi.model.repositories;

import com.kpi.model.entities.Enrollee;
import com.kpi.model.utilities.JSONConverter;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

public class EnrolleeFileRepository implements EnrolleeRepository {

    private final File file;

    public EnrolleeFileRepository(File file) {
        this.file = file;
    }

    @Override
    public List<Enrollee> getAll() throws IOException {
        String wholeFile = Files.lines( file.toPath() )
                .reduce( (accumulator, currentValue) -> accumulator + currentValue )
                .orElse( "" );
        return JSONConverter.jsonToEnrolleeList(wholeFile);
    }

    @Override
    public void save(List<Enrollee> entities) throws IOException {
        String json = JSONConverter.enrolleeListToJson(entities);
        FileWriter fw = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(json);
        writer.close();
    }
}
