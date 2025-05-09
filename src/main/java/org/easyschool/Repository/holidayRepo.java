package org.easyschool.Repository;

import org.easyschool.Model.holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface holidayRepo extends JpaRepository<holiday, Long> {

}
