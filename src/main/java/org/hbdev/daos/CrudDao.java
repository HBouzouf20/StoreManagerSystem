package org.hbdev.daos;

import java.util.List;

public interface CrudDao<T,ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T t);
    T update(T t);
    void delete(T t);
}
