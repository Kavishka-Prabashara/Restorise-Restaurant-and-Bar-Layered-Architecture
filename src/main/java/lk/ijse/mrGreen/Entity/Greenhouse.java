package lk.ijse.mrGreen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Greenhouse {
    private String id ;
    private String name;
    private String l_id;
    private int seed;
    private int temp ;
    private double ph;
}
