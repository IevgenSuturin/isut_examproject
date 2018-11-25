package ua.skillsup.examproject.isut.dao;

public interface CrudRepository <T, ID>  {
        long create(T t);
        void update(T t);
        boolean delete(T t);
        T getOne(ID id);
        Iterable<T> findAll();
}
