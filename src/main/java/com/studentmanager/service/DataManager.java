package com.studentmanager.service;

import com.studentmanager.exception.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic data manager with thread-safe operations
 */
public class DataManager<T> implements DataOperations<T> {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);
    private final List<T> items = Collections.synchronizedList(new ArrayList<>());
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(T item) throws ValidationException {
        lock.writeLock().lock();
        try {
            logger.info("Adding item to data manager");
            items.add(item);
            logger.info("Current items count: {}", items.size());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void remove(T item) {
        lock.writeLock().lock();
        try {
            logger.info("Removing item from data manager");
            items.remove(item);
            logger.info("Current items count after removal: {}", items.size());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<T> getAll() {
        lock.readLock().lock();
        try {
            logger.info("Getting all items, count: {}", items.size());
            return new ArrayList<>(items);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void update(T item) {
        lock.writeLock().lock();
        try {
            int index = items.indexOf(item);
            if (index >= 0) {
                items.set(index, item);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
} 