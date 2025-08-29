package lk.ijse.mrGreen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lettuce {
    private String id;
    private String name;
    private int temp;
    private int humid;
    private double qty;
    private double seed;
    private double unit;
    private String suppId;
}
