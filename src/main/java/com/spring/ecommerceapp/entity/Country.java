package com.spring.ecommerceapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "country")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id")
    private int id;

    @Column(name ="code")
    private String code;

    @Column(name ="name")
    private String name;

    //oneToMany with state
    @OneToMany(mappedBy = "country")
    @JsonIgnore // will ignore the states
    private List<State> states;
}
