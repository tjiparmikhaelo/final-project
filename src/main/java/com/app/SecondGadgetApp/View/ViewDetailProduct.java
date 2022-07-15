package com.app.SecondGadgetApp.View;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_detail_product")
@Setter
@Getter
public class ViewDetailProduct
{
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Long price;

    @Column(name = "product_photo")
    private byte[] productPhoto;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;
}
