package org.easyschool.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "class")
public class Classes extends commonEntity{

    @Id
    @GeneratedValue(generator = "increment")
    private int classId;
    @NotNull(message = "Class name cannot be null")
    private String className;
    @OneToMany(mappedBy = "classes", fetch = FetchType.EAGER )
    private Set<person> persons;


}
