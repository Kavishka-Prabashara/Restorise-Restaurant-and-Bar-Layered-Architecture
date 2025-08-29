package lk.ijse.mrGreen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fertilizer {
    private String id;
    private String name;
    private String company;
    private double unit;
    private int qty;
    private String supId;
    private String lId;
}
