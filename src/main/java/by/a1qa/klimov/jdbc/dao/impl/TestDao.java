package by.a1qa.klimov.jdbc.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.jdbc.dao.interfaces.Dao;
import by.a1qa.klimov.jdbc.entity.Test;

import java.sql.*;
import java.util.Optional;

public class TestDao implements Dao<Test> {
    private static final String TABLE_NAME = "TEST";

    @Override
    public long create(Test test) {
        Logger.getInstance().info("Create Test: " + test.toString());
        long id = 0;
        return id;
    }

    @Override
    public Test read(long id) {
        Logger.getInstance().info("Read Test with id: " + id);
        return null;
    }

    @Override
    public int update(Test test) {
        Logger.getInstance().info("Update Test: " + test.toString());
        return 0;
    }

    @Override
    public int delete(long id) {
        Logger.getInstance().info("Delete Test with id: " + id);
        return 0;
    }
}

