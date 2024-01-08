package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddtoCartTM {
    private final SimpleStringProperty itemName;
    private final SimpleDoubleProperty unitPrice;
    private final SimpleIntegerProperty qty;
    private final SimpleDoubleProperty total;
    private final SimpleStringProperty itemType;
}
