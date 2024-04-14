package com.example.michaeltraining.user;

import com.example.michaeltraining.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "users")
@Builder
public class User extends BaseEntity {

    private String name;
    private String surname;
    private Long age;
}
