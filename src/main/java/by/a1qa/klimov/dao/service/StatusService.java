package by.a1qa.klimov.dao.service;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.impl.StatusDaoImpl;
import by.a1qa.klimov.dao.interfaces.StatusDao;

import java.util.List;

public class StatusService {
    private StatusDao statusDao = new StatusDaoImpl();

    public long addStatus(Status status) {
        if (status != null) {

            Long id = status.getId();
            if (id != null && statusDao.read(status.getId()) != null) {
                Logger.getInstance().error("Status with id \"" + id + "\" already exists.");
                return 0;
            }

            List<Status> statusList = statusDao.find(status);
            if (statusList.size() == 0) return statusDao.create(status);
            return statusList.get(0).getId();
        } else return 0;
    }
}
