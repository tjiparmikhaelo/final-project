package com.app.SecondGadgetApp.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Products
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "product_photo")
    private byte[] productPhoto;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
