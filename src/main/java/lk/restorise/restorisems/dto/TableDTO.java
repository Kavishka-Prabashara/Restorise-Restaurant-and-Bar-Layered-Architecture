package lk.restorise.restorisems.dto;

public class TableDTO {
    private int tableID;
    private int noOfChairs;

    public TableDTO() {
    }

    public TableDTO(int tableID, int noOfChairs) {
        this.tableID = tableID;
        this.noOfChairs = noOfChairs;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getNoOfChairs() {
        return noOfChairs;
    }

    public void setNoOfChairs(int noOfChairs) {
        this.noOfChairs = noOfChairs;
    }
}
