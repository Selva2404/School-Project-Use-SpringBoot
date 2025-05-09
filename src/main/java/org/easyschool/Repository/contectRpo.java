package org.easyschool.Repository;

import org.easyschool.Model.Contect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface contectRpo extends JpaRepository<Contect, Integer> {

    List<Contect> findByStatus(String open);
}
