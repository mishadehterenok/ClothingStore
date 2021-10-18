package com.mishadehterenok.project.service;

import java.util.List;

public interface BaseService <T>{

    List<T> findAll();
    void save(T t);
    T findById(Long id);
}
