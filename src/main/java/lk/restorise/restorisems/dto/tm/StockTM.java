package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockTM {
    private final SimpleIntegerProperty stockQty;
    private final SimpleStringProperty suppliedDate;
    private final SimpleStringProperty suppliedItemID;
    private final SimpleStringProperty suppliedItemType;
    private final SimpleStringProperty supplierID;
    private final SimpleStringProperty supplierName;

    public StockTM(String supplierID, String supplierName,String suppliedItemID,String suppliedItemType,int stockQty, String suppliedDate  ) {
        this.supplierID = new SimpleStringProperty(supplierID);
        this.supplierName = new SimpleStringProperty(supplierName);
        this.suppliedItemID = new SimpleStringProperty(suppliedItemID);
        this.suppliedItemType = new SimpleStringProperty(suppliedItemType);
        this.stockQty = new SimpleIntegerProperty(stockQty);
        this.suppliedDate = new SimpleStringProperty(suppliedDate);
    }

    public int getStockQty() {
        return stockQty.get();
    }

    public SimpleIntegerProperty stockQtyProperty() {
        return stockQty;
    }

    public String getSuppliedDate() {
        return suppliedDate.get();
    }

    public SimpleStringProperty suppliedDateProperty() {
        return suppliedDate;
    }

    public String getSuppliedItemID() {
        return suppliedItemID.get();
    }

    public SimpleStringProperty suppliedItemIDProperty() {
        return suppliedItemID;
    }

    public String getSuppliedItemType() {
        return suppliedItemType.get();
    }

    public SimpleStringProperty suppliedItemTypeProperty() {
        return suppliedItemType;
    }

    public String getSupplierID() {
        return supplierID.get();
    }

    public SimpleStringProperty supplierIDProperty() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName.get();
    }

    public SimpleStringProperty supplierNameProperty() {
        return supplierName;
    }
}
