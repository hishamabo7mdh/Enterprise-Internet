package com.example.dataSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_ip")
public class UserIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String ip;
    @OneToOne
    @JoinColumn(name = "person_id")
    Person person;
    @Override
    public String toString() {
        return "UserIp{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", person=" + (person != null ? person.getId() : "null") + // تجنب الدورة اللا نهائية
                '}';
    }
}
