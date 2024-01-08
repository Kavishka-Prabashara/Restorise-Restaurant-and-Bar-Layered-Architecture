package lk.restorise.restorisems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddtoCartDTO {
    private String itemName;
    private double unitPrice;
    private int qty;
    private double total;
    private String itemType;
}