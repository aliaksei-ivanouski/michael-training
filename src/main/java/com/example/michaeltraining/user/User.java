package com.example.michaeltraining.user;

import com.example.michaeltraining.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "USERS", schema = "PUBLIC")
public class User extends BaseEntity {

    private String name;
    private String surname;
    private Long age;


    public interface Projection extends Serializable {}

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class NewUserProjection implements Projection {
        private String name;
        private String surname;
        private Long age;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class UserProjection implements Projection {
        private UUID uuid;
        private String name;
        private String surname;
        private Long age;
        protected Instant createdAt;
        protected Instant updatedAt;
    }
}
