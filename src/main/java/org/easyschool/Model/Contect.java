package org.easyschool.Model;



import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.IdGeneratorType;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@Data
@RequestScope
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "contact_msg")
public class Contect extends commonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @IdGeneratorType(CustomSequenceGenerator.class)
    private int contact_id;
    @NotBlank(message = "please fill the value")
    @NotNull
    private String name;
    @NotBlank(message = "please fill the value")
    @Positive
    @NotNull
   // @Column(name = "mobile_num")
    private String mobileNum;
    @NotNull
    @NotBlank(message = "please fill the value")
   //@Column(name ="mail_id", nullable = false)
    private String mail_id;
    @NotNull
    @NotBlank(message = "please fill the value")
    private String subject;
    @NotNull
    @NotBlank(message = "please fill the value")
    private String message;

    private String status;



}
