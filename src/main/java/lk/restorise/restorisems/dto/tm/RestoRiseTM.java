package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.model.RestoRiseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestoRiseTM {

    private final StringProperty itemName = new SimpleStringProperty();
    private final DoubleProperty unitPrice = new SimpleDoubleProperty();
    private final IntegerProperty qty = new SimpleIntegerProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();
    private final StringProperty itemType = new SimpleStringProperty();

    private ObservableList<RestoRiseModel> restoriseList = FXCollections.observableArrayList();

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public int getQty() {
        return qty.get();
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public String getItemType() {
        return itemType.get();
    }

    public StringProperty itemTypeProperty() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType.set(itemType);
    }
}
