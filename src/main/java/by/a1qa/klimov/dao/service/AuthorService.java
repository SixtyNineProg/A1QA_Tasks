package by.a1qa.klimov.dao.service;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Author;
import by.a1qa.klimov.dao.impl.AuthorDaoImpl;
import by.a1qa.klimov.dao.interfaces.AuthorDao;

import java.util.List;

public class AuthorService {
    private AuthorDao authorDao = new AuthorDaoImpl();

    public long addAuthor(Author author) {
        if (author != null) {

            Long id = author.getId();
            if (id != null && authorDao.read(author.getId()) != null) {
                Logger.getInstance().error("Author with id \"" + id + "\" already exists.");
                return 0;
            }

            List<Author> authorList = authorDao.find(author);
            if (authorList.size() == 0) {
                authorList = authorDao.findByLogin(author.getLogin());
                if (authorList.size() != 0) {
                    Logger.getInstance().error("Author with login \"" + author.getLogin() + "\" exists.");
                    return 0;
                }
                return authorDao.create(author);
            }
            return authorList.get(0).getId();
        } else return 0;
    }

    public void delete(long id) {
        authorDao.delete(id);
    }
}
