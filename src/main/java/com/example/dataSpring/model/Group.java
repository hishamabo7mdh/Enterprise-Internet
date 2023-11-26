package com.example.dataSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "my_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties(value = "group", allowSetters = true)
    private List<PersonGroup> personGroups;

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties(value = "group", allowSetters = true)
    private List<FileGroup> fileGroups;
}
