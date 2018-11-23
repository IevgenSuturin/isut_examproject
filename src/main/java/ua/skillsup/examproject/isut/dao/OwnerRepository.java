package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Owner;

import java.util.Optional;

public interface OwnerRepository {
    long create(Owner owner);
    void update(Owner owner);
    boolean delete(long ownerId);
    Owner getOne(long id);
    Iterable<Owner> findAll();
    Optional<Owner> findByFirstName(String fname);
}
