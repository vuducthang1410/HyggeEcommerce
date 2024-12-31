package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tbl_shop")
public class Shop extends BaseEntity{
    @Column(unique=true, nullable=false,length = 255,name = "NAME")
    private String name;
    @Column(unique=true, nullable=false,length = 200,name = "ADDRESS")
    private String address;
    @Column(nullable=false,length = 11,name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(nullable=false,length = 40,name = "EMAIL")
    private String email;
    @Column(nullable=false,name = "RATING")
    private float rating;
    @Column(nullable = false,name = "OWNER_ID")
    private String ownerId;
    @Column(nullable = false,name = "DESCRIPTION")
    @Lob
    private Byte[] description;
}
