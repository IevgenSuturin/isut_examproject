package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Owner;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByFirstName(String fname);
    boolean exist(Long aLong);
}
