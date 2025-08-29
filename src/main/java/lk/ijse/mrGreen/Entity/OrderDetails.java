package lk.ijse.mrGreen.Entity;

import lk.ijse.mrGreen.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    private String orderId;
    private List<CartTm> cartTmList;
}
