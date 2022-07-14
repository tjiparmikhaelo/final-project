package com.app.SecondGadgetApp.View;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_detail_produk")
@Setter
@Getter
public class VwDetailProduct
{
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "status")
    private String status;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Long price;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "address")
    private String address;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;
}
