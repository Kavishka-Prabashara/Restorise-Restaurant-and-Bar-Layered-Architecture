package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleIntegerProperty;

public class TableTM {
    private final SimpleIntegerProperty tableID;
    private final SimpleIntegerProperty noOfChairs;

    public TableTM(int tableID, int noOfChairs) {
        this.tableID = new SimpleIntegerProperty(tableID);
        this.noOfChairs = new SimpleIntegerProperty(noOfChairs);
    }

    public int getTableID() {
        return tableID.get();
    }

    public SimpleIntegerProperty tableIDProperty() {
        return tableID;
    }

    public int getNoOfChairs() {
        return noOfChairs.get();
    }

    public SimpleIntegerProperty noOfChairsProperty() {
        return noOfChairs;
    }
}
