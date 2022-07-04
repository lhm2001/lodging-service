package upc.edu.pe.lodgingservice.services;

import java.util.List;

public interface CrudService<T,ID> {
    List<T> findAll() throws Exception;
}
