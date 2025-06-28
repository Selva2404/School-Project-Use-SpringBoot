package org.easyschool.Repository;

import jakarta.transaction.Transactional;
import org.easyschool.Model.Contect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface contectRpo extends JpaRepository<Contect, Integer> {

    @Query("select c from Contect c where c.status= :open")
    List<Contect> findByStatus(String open);

    //@Query(value = "select * from contact_msg c where c.status= :ope", nativeQuery = true)
    Page<Contect> findByStatus(String open, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value="UPDATE contact_msg Set status = ?1 where contact_id = ?2", nativeQuery = true)
    void UpdateStatusMsg(String Status,int id);

    @Transactional
    @Modifying
    void updateMsgStatus(String status, int id);


    Page<Contect> findOpenMses(@Param("status") String status, Pageable pageable);

    @Query(nativeQuery = true)
    Page<Contect> findOpenMsesNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    void updateMsgStatusNative(String status, int id);

}
