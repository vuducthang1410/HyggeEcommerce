package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, length = 50, name = "ID")
    private String id;
    @CreatedBy
    @Column(updatable = false, nullable = false, length = 50, name = "CREATED_BY")
    private String createdBy;
    @LastModifiedBy
    @Column(length = 50,name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;
    @CreatedDate
    @Column(updatable = false, nullable = false, length = 50, name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column( length = 50,name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;
    @Column( length = 1,name = "IS_DELETED")
    private Boolean isDelete;
}
