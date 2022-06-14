package com.kpi.model.repositories;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    List<T> getAll() throws IOException;

    void save(List<T> entities) throws IOException;

}
