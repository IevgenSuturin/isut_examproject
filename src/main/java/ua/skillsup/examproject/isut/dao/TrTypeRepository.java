package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.TransTypes;

import java.util.List;

public interface TrTypeRepository {
    TransTypes getTrTypeById(long id);
    Iterable<TransTypes> getAll();
    long createTrType(TransTypes transTypes);
}
