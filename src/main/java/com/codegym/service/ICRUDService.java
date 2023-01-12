package com.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICRUDService<T,Long> {
    Page<T> findAll(Pageable page);

    T findById(Long id);

    void save(T t);

    void delete(Long id);
    Page<T> findByName(String name, Pageable pageable);


}
