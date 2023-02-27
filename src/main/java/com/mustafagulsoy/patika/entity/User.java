package com.mustafagulsoy.patika.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id; // kimlik numarası
    private String name; // ad
    private String surname; // soyad
    private float income; // gelir
    private String phone; // Telefon bilgisi
    private String birthDate; // doğum tarihi
    private float assurance; // Teminat
    private int creditRate; //  kredi skoru
    // jwt parameter
    private String username;
    private String password;
    private float creditLimit;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
