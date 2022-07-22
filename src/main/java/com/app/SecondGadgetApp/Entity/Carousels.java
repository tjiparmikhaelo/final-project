package com.app.SecondGadgetApp.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "carousel")
public class Carousels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carousel")
    private Long carouselId;

    @Column(name = "carousel_name",unique = true)
    private String carouselName;

    @Column(name = "carousel_link")
    private String link;

    @Lob
    @Column(name = "img")
    private String img;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
