package model.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Medicine {
    private String id;
    private String brandName;
    private String genaricsName;
    private String dosage;
    private String expireDate;
    private int currentStock;
    private double unitPrice;
}
