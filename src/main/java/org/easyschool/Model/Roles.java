package org.easyschool.Model;

import jakarta.persistence.*;
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
  //  @Column(name = "role_id")
    private int roleid;
    @NotNull
    @NotBlank()
    private String roleName;
}
