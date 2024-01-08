package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlaceOrderTM {
    private final SimpleStringProperty itemCategory;
    private final SimpleStringProperty itemName;
    private final SimpleDoubleProperty itemUnitPrice;
    private final SimpleIntegerProperty itemQTY;
    private final SimpleDoubleProperty itemTotal;
    private final SimpleStringProperty suppliedItemID;

// constructor and other methods

    public String getSuppliedItemID() {
        return suppliedItemID.get();
    }

    public SimpleStringProperty suppliedItemIDProperty() {
        return suppliedItemID;
    }


    public PlaceOrderTM(String itemCategory, String itemName, double itemUnitPrice, int itemQTY, double itemTotal) {
        this.itemCategory = new SimpleStringProperty(itemCategory);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemUnitPrice = new SimpleDoubleProperty(itemUnitPrice);
        this.itemQTY = new SimpleIntegerProperty(itemQTY);
        this.itemTotal = new SimpleDoubleProperty(itemTotal);
        this.suppliedItemID = new SimpleStringProperty("");
    }

    public SimpleStringProperty itemCategoryProperty() {
        return itemCategory;
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public SimpleDoubleProperty itemPriceProperty() {
        return itemUnitPrice;
    }

    public SimpleIntegerProperty itemQTYProperty() {
        return itemQTY;
    }

    public SimpleDoubleProperty itemTotalProperty() {
        return itemTotal;
    }

    public String getItemCategory() {
        return itemCategory.get();
    }

    public String getItemName() {
        return itemName.get();
    }

    public double getItemUnitPrice() {
        return itemUnitPrice.get();
    }

    public int getItemQTY() {
        return itemQTY.get();
    }

    public double getItemTotal() {
        return itemTotal.get();}
}