package org.easyschool.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.easyschool.Annotation.Failedvalied;
import org.easyschool.Annotation.Passwordvalid;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
@Failedvalied.List({@Failedvalied(
        field = "pwd",
        fieldMatch = "confirmPwd",
        message = " password do not match"
),
@Failedvalied(
        field = "mailid",
        fieldMatch = "conform_mail_id",
        message = " email do not match"
)})
public class person extends commonEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    @OneToOne(fetch =FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = Address.class)
    @JoinColumn(name = "addressid",referencedColumnName = "addressid",
            nullable = true)
    private Address address;

    @OneToOne(fetch =FetchType.EAGER,cascade=CascadeType.PERSIST,targetEntity = Roles.class)
    @JoinColumn(name = "roleid",referencedColumnName = "roleid",
            nullable = true)
    private Roles role;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "classId", referencedColumnName = "classid", nullable = true)
    private Classes classes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,targetEntity = Courses.class)
    @JoinTable(name = "person_courses",
    joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "person_id")},
    inverseJoinColumns = {
            @JoinColumn(name = "courses_id", referencedColumnName = "coursesId")})
    private Set<Courses> courses=new HashSet<>();
    @NotNull
    @NotBlank(message ="please fill the value")
    private String name;
    @NotNull
    @Positive
    @NotBlank(message ="please fill the mobile Number")
    private String mobileNum;
    @NotNull
    @Email
    @NotBlank(message ="please fill the mail id")
    private String mailid;
    @NotNull
    @NotBlank(message ="please fill the confirm mail id")
    @Email
    @Transient
    private String conform_mail_id;

    @NotNull
    @NotBlank(message ="please fill the password")
    @Passwordvalid
    private String pwd;

    @NotNull
    @NotBlank(message ="please fill the confirm password")
   @Transient
    private String confirmPwd;








    // Getters and Setters
}
