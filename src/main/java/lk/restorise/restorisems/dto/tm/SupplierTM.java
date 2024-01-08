package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SupplierTM {

    private final StringProperty txtSupplierID = new SimpleStringProperty();
    private final StringProperty txtSupplierName = new SimpleStringProperty();
    private final StringProperty txtEmpMobile = new SimpleStringProperty();
    private final StringProperty txtSupplierEmail = new SimpleStringProperty();
    private final StringProperty txtSuppyItemID = new SimpleStringProperty();
    private final StringProperty cbSupplyItem = new SimpleStringProperty();
    private final StringProperty dtSupplyingDate = new SimpleStringProperty();
    private final StringProperty txtSuppliedStockQTY = new SimpleStringProperty();


    // Add getters and setters for txtSuppyItemID and cbSupplyItem
    public String getTxtSuppyItemID() {
        return txtSuppyItemID.get();
    }

    public void setTxtSuppyItemID(String txtSuppyItemID) {
        this.txtSuppyItemID.set(txtSuppyItemID);
    }

    public StringProperty txtSuppyItemIDProperty() {
        return txtSuppyItemID;
    }

    public String getCbSupplyItem() {
        return cbSupplyItem.get();
    }

    public void setCbSupplyItem(String cbSupplyItem) {
        this.cbSupplyItem.set(cbSupplyItem);
    }

    public StringProperty cbSupplyItemProperty() {
        return cbSupplyItem;
    }

    public String getTxtSuppliedStockQTY() {
        return txtSuppliedStockQTY.get();
    }

    public void setTxtSuppliedStockQTY(String txtSuppliedStockQTY) {
        this.txtSuppliedStockQTY.set(txtSuppliedStockQTY);
    }

    public StringProperty txtSuppliedStockQTYProperty() {
        return txtSuppliedStockQTY;
    }

}
