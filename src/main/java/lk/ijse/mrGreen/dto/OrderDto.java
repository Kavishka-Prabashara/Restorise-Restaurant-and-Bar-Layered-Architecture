package lk.ijse.mrGreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private String OrderId;
    private String CusId;
    private LocalDate date;
}
