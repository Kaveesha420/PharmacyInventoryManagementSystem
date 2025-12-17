package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    private String id;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
}
