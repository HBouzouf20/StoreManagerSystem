package org.hbdev.daos;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T, ID extends Serializable> {
    T save(T t);
    T update(T t);
    void delete(T t);
    T findById(ID id);
    List<T> findAll();
}
