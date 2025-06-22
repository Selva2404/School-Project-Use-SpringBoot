package org.easyschool.Repository;

import org.easyschool.Model.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface personRepository extends JpaRepository<person, Integer> {

    person readByMailid(String email);

    Optional<person> findById(int personId);
}
