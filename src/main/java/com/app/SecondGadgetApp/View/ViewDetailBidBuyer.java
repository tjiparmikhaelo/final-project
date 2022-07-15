package com.app.SecondGadgetApp.View;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "vw_detail_bid_buyer")
@Setter
@Getter
public class ViewDetailBidBuyer
{
    @Id
    @Column(name = "bid_id")
    private Long bidId;

    @Column(name = "bid_status")
    private String bidStatus;

    @Column(name = "bid_price")
    private Long bidPrice;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private Long productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_photo")
    private byte[] productPhoto;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "img")
    private byte[] img;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}
