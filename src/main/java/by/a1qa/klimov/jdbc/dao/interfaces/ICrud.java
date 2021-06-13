package by.a1qa.klimov.jdbc.dao.interfaces;

public interface ICrud<T> {
    long create(T object);
    T read(long id);
    int update(T object);
    int delete(long id);
}
