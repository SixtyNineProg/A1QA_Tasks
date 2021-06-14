package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Test;
import by.a1qa.klimov.dao.interfaces.Dao;

public class TestDao implements Dao<Test> {

    @Override
    public long create(Test test) {
        Logger.getInstance().info("Create Test: " + test.toString());
        return 0;
    }

    @Override
    public Test read(long id) {
        Logger.getInstance().info("Read Test with id: " + id);
        return null;
    }

    @Override
    public void update(Test test) {
        Logger.getInstance().info("Update Test: " + test.toString());
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Test with id: " + id);
    }
}

