package org.easyschool.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Courses extends commonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coursesId;
    @NotNull
    @NotBlank(message="please fill the confirm password")
    private String coursesName;
    @NotNull
    @NotBlank(message="please fill the confirm password")
    private int fees;
    @ManyToMany(mappedBy ="courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<person> persons=new HashSet<>();





}
