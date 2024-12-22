package com.studentmanager.service;

import java.util.List;
import com.studentmanager.exception.ValidationException;

public interface DataOperations<T> {
    void add(T item) throws ValidationException;
    void remove(T item);
    List<T> getAll();
    void update(T item);
} 