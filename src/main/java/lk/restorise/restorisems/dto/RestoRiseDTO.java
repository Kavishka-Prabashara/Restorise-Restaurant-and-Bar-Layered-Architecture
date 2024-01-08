package lk.restorise.restorisems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestoRiseDTO {
    private String itemName;
    private double unitPrice;
    private int qty;
    private double total;
    private String itemType;

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
