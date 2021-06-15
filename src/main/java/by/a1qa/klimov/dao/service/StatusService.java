package by.a1qa.klimov.dao.service;

import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.impl.StatusDaoImpl;

public class StatusService {
    private StatusDaoImpl statusDaoImpl = new StatusDaoImpl();

    public void addStatus(Status status) {
        if (status != null) {
            Status existStatus = statusDaoImpl.find(status);
        }
    }
}
