package by.a1qa.klimov.dao.service;

import by.a1qa.klimov.dao.entity.Author;
import by.a1qa.klimov.dao.impl.AuthorDaoImpl;
import by.a1qa.klimov.dao.interfaces.AuthorDao;

import java.util.List;

public class AuthorService {
    private AuthorDao authorDao = new AuthorDaoImpl();

    public long addAuthor(Author author) {
        if (author != null) {
            List<Author> authorList = authorDao.find(author);
            if (authorList.size() == 0) {
                authorList = authorDao.findByLogin(author.getName());
                if (authorList.size() != 0)
                    throw new IllegalArgumentException("Project with login " + author.getLogin() + "exists.");
                return authorDao.create(author);
            }
            return authorList.get(0).getId();
        } else return 0;
    }
}
