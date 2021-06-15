package by.a1qa.klimov.dao.service;

import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.impl.StatusDaoImpl;

import java.util.List;

public class StatusService {
    private StatusDaoImpl statusDaoImpl = new StatusDaoImpl();

    public long addStatus(Status status) {
        if (status != null) {
            List<Status> statusList = statusDaoImpl.find(status);
            if (statusList.size() == 0) return statusDaoImpl.create(status);
            return statusList.get(0).getId();
        } else return 0;
    }
}
