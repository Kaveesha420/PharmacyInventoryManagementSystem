package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @Column(name = "medicine_id")
    private String id;
    private String brandName;
    private String genaricsName;
    private String dosage;
    private Date expireDate;
    private int currentStock;
    private double unitPrice;
}
