package com.app.SecondGadgetApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

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

//    @Lob
    @Column(name = "image_product")
    private String imageProduct;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(
            mappedBy = "products",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ImageProducts> imageProductsSet;
}
