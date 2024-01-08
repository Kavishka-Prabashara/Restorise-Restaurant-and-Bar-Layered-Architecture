package lk.restorise.restorisems.dto;

import lombok.Getter;

@Getter
public class StockDTO {
    private final String supplierID;
    private final String supplierName;
    private final String suppliedItemID;
    private final String suppliedItemType;
    private final int stockQty;
    private final String suppliedDate;
    public StockDTO( String supplierID, String supplierName, String suppliedItemID, String suppliedItemType,int stockQty, String suppliedDate) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.suppliedItemID = suppliedItemID;
        this.suppliedItemType = suppliedItemType;
        this.stockQty = stockQty;
        this.suppliedDate = suppliedDate;
    }

}
