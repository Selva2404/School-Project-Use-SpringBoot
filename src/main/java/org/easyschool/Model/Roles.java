package org.easyschool.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Roles extends commonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleid;
    @NotNull
    @NotBlank()
    private String roleName;
}
