package com.example.dataSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "files")
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "file_name")
    private String name;
    @Column(name = "file_status")
    private boolean status;
    @Column(name = "file_pinding")
    private boolean pinding;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties(value = "files", allowSetters = true)
    private Person owner;

//    @OneToMany(mappedBy = "fileFK")
//    @JoinColumn(name = "file_Group_FK")
//    @JsonIgnoreProperties(value = "fileFK",allowSetters = true)
//    private List<FileGroup>fileGroubs;

}
