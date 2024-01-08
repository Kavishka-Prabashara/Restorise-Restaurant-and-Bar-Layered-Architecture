package lk.restorise.restorisems.dto;

import lombok.Getter;

import java.time.LocalDate;


public class SupplierDTO {

    @Getter
    private String txtSupplierID;
    @Getter
    private String txtSupplierName;
    private int txtSupplierMobile;
    @Getter
    private String txtSupplierEmail;
    @Getter
    private String txtSuppyItemID;
    @Getter
    private String cbSupplyItem;
    @Getter
    private LocalDate dtSupplyingDate;

    @Getter
    private int txtSupplySTOCKQTY;



    public SupplierDTO(String supplierId, String supplierName, int supplierMobile, String supplierIdEmail, String supplyItemId, String cbSupplyItem, LocalDate employeeDob, int suppliedStockQTY) {
        this.txtSupplierID = supplierId;
        this.txtSupplierName = supplierName;
        this.txtSupplierMobile = supplierMobile;
        this.txtSupplierEmail = supplierIdEmail;
        this.txtSuppyItemID=supplyItemId;
        this.cbSupplyItem = cbSupplyItem;
        this.dtSupplyingDate = employeeDob;
        this.txtSupplySTOCKQTY = suppliedStockQTY;
    }

    public void setTxtSupplierID(String txtSupplierID) {
        this.txtSupplierID = txtSupplierID;
    }

    public void setTxtSupplierName(String txtSupplierName) {
        this.txtSupplierName = txtSupplierName;
    }

    public int getTxtEmpMobile() {
        return txtSupplierMobile;
    }

    public void setTxtEmpMobile(int txtEmpMobile) {
        this.txtSupplierMobile = txtEmpMobile;
    }

    public void setTxtSupplierEmail(String txtSupplierEmail) {
        this.txtSupplierEmail = txtSupplierEmail;
    }

    public void setTxtSuppyItemID(String txtSuppyItemID) {
        this.txtSuppyItemID = txtSuppyItemID;}

    public void setCbSupplyItem(String cbSupplyItem) {
        this.cbSupplyItem = cbSupplyItem;
    }

    public void setDtSupplyingDate(LocalDate dtSupplyingDate) {
        this.dtSupplyingDate = dtSupplyingDate;
    }

    public void setSuppliedStockQTY(int suppliedStockQTY) {
        this.txtSupplySTOCKQTY = suppliedStockQTY;
    }




    @Override
    public String toString() {
        return "SupplierDTO{" +
                "txtSupplierID='" + txtSupplierID + '\'' +
                ", txtSupplierName='" + txtSupplierName + '\'' +
                ", txtEmpMobile=" + txtSupplierMobile +
                ", txtSupplierEmail='" + txtSupplierEmail + '\'' +
                ", txtSuppyItemID='" + txtSuppyItemID + '\'' +
                ", cbSupplyItem='" + cbSupplyItem + '\'' +
                ", dtSupplyingDate=" + dtSupplyingDate +
                ", txtSupplySTOCKQTY="+ txtSupplySTOCKQTY+
                '}';
    }


    public int getSuppliedStockQTY() {
        return txtSupplySTOCKQTY;
    }
}
