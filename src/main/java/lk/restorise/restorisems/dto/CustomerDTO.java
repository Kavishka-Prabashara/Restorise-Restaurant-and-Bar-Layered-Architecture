package lk.restorise.restorisems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date; // Import the Date class

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerDTO {
    private String id;
    private String name;
    private String mobileNo;
    private String orderID;
    private Date orderDate;
}
