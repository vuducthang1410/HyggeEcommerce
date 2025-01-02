package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_category")
public class Category extends BaseEntity {
    @Column(unique = true, nullable = false,length = 150,name = "NAME")
    private String name;
    @Column(nullable = false,name = "DESCRIPTION")
    @Lob
    private Byte[] description;
    @Column(nullable = true,length = 50,name = "PARRENT_ID")
    private String parentId;
    @Column(unique = true, nullable = false,length = 150,name = "URL_IMAGE")
    private String urlImage;
}
