package com.app.SecondGadgetApp.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long idCity;

    @Column(name = "no_city")
    private  String noCity;

    @Column(name = "city_name")
    private String cityName;
}
