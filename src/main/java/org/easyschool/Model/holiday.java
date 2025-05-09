package org.easyschool.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="holidays")
public class holiday extends commonEntity{

    @Id
   // @Column(name = "holiday_name")
    private  String holidayName;
   // @Column(name = "holiday_date")
    private  String holidayDate;
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private  Type type;



    public enum Type {
        FESTIVAL, FEDERAL
    }

}
