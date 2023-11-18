package com.example.dataSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "person")
public class Person{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "person_name")
    private String name;
    @Column(name = "person_password")
    private String password;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties(value = "owner", allowSetters = true)
    private List<MyFile> files;

    @OneToOne(mappedBy = "person")
    @JsonIgnoreProperties(value = "person", allowSetters = true)
    UserIp userIp;

    @OneToMany(mappedBy = "person")
    @JsonIgnoreProperties(value = "person", allowSetters = true)
    private List<PersonGroup> personGroups;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
}
