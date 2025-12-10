package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineDto {
    private String brandName;
    private String genaricsName;
    private String dosage;
    private String expireDate;
    private int currentStock;
    private double unitPrice;
}
