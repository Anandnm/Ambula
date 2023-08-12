package com.Ambula.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(name = "username" , nullable = false)
    private String username;
    @Column(name="role" , nullable = false)
    private  String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Location> locations;
}
