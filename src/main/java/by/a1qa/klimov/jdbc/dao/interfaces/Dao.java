package by.a1qa.klimov.jdbc.dao.interfaces;

import java.util.Optional;

public interface Dao<T> {
    long create(T object);
    T read(long id);
    int update(T object);
    int delete(long id);
}
