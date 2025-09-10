package org.hbdev.services;

import java.util.List;

public interface CrudService<M, ID> {
    public M save(M m);
    public M update(M m);
    public M findById(ID id);
    public List<M> findAll();
    public void deleteById(ID id);
}
