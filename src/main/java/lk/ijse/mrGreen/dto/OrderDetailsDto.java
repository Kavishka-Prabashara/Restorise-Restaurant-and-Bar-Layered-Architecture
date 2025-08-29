package lk.ijse.mrGreen.dto;

import lk.ijse.mrGreen.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private String orderId;

    private List<CartTm> cartTmList;
}
