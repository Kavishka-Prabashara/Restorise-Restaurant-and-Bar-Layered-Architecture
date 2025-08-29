package lk.ijse.mrGreen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String OrderId;
    private String CusId;
    private LocalDate date;
}
