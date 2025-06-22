package org.easyschool.Model;



import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.web.context.annotation.RequestScope;


@Data
@RequestScope
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "contact_msg")
@SqlResultSetMappings( {
        @SqlResultSetMapping(name = "SqlResultSetMapping.count",
                columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name="Contect.findOpenMses", query="Select c from Contect c where c.status= :status"),
        @NamedQuery(name="Contect.updateMsgStatus", query = "update Contect c Set c.status=?1 where c.contact_id=?2")

})
@NamedNativeQueries({
        @NamedNativeQuery(name="Contect.findOpenMsesNative", query="select * from contect_msg c where c.status= :status", resultClass = Contect.class),
        @NamedNativeQuery(name="Contect.findOpenMsesNative.count",query="select COUNT(*) as cnt from contect_msg c where c.status=:status",
                resultSetMapping = "SqlResultSetMapping.count"
                ),
        @NamedNativeQuery(name="Contect.updateMsgStatusNative", query = "UPDATE contact_msg c Set c.status = ?1 where c.contact_id = ?2")
})
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
