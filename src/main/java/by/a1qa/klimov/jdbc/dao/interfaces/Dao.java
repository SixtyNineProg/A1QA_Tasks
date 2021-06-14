package by.a1qa.klimov.jdbc.dao.interfaces;

public interface Dao<T> {
    long create(T object);

    T read(long id);

    void update(T object);

    void delete(long id);
}
