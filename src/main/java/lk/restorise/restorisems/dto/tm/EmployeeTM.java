package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@AllArgsConstructor
@Getter
@Setter

public class EmployeeTM {



    private final StringProperty txtEmpId = new SimpleStringProperty();
    private final StringProperty txtEmpName = new SimpleStringProperty();
    private final StringProperty txtEmpemail = new SimpleStringProperty();
    private final StringProperty txtEmpMobile = new SimpleStringProperty();
    private final StringProperty empDate = new SimpleStringProperty();
    private final StringProperty dtSupplyingDate = new SimpleStringProperty();


}
