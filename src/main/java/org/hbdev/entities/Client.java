package org.hbdev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hbdev.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthday;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
