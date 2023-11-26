package com.example.dataSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person_group")
public class PersonGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id",nullable = false,referencedColumnName = "id")
    @JsonIgnoreProperties(value = "person", allowSetters = true)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id",nullable = false,referencedColumnName = "id")
    @JsonIgnoreProperties(value = "person", allowSetters = true)
    private Group group;

    @Override
    public String toString() {
        return "PersonGroup{" +
                "id=" + id +
                ", person=" + (person != null ? person.getId() : null) +
                ", group=" + (group != null ? group.getId() : null) +
                '}';
    }

}
