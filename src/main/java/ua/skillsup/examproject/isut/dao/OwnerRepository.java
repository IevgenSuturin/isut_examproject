package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<OwnerDto> findByFirstName(String firstName);
    boolean exist(Long aLong);
}
