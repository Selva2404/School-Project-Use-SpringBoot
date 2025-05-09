package org.easyschool.Model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class commonEntity {

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
    @CreatedBy
    @Column(updatable = false)
    String createdBy;
    @LastModifiedDate
    @Column(updatable = false)
    LocalDateTime updateAt;
    @LastModifiedBy
    @Column(updatable = false)
    String updateBy;
}
