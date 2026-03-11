package org.easyschool.Repository;

import org.easyschool.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findByOrderByCoursesName();
    List<Courses> findByOrderByCoursesNameDesc();
}
