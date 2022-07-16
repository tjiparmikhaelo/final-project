//package com.app.SecondGadgetApp.Entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Entity
//@Table(name = "bids")
//@Setter
//@Getter
//public class Bids
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "bid_id")
//    private Long bidId;
//
//    @Column(name = "buyer_id")
//    private Long buyerId;
//
//    @Column(name = "product_id")
//    private Long productId;
//
//    @Column(name = "bid_price")
//    private BigDecimal bidPrice;
//
//    @Column(name = "bid_status")
//    private String bidStatus;
//
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private Date updatedAt;
//}
