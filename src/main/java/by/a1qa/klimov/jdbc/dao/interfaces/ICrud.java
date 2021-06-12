package by.a1qa.klimov.jdbc.dao.interfaces;

public interface ICrud<T> {
    long create(T object);
    T read(long id);
    long update(T object);
    long delete(long id);
}
