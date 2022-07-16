package com.app.SecondGadgetApp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "image_product")
@Setter
@Getter
public class ImageProducts
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_product_id")
    private Long imageProductId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name = "image_url")
    private String imageUrl;
}
