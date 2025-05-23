package org.easyschool.Repository;

import org.easyschool.Model.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personRepository extends JpaRepository<person, Long> {

    person readByMailid(String email);
}
