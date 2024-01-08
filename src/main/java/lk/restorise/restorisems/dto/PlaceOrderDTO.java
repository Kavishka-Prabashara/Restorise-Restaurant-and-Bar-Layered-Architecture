package lk.restorise.restorisems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDTO {
    private String itemCategory;
    private String itemName;
    private double itemUnitPrice;
    private int itemQTY;
    private double itemTotal;
}