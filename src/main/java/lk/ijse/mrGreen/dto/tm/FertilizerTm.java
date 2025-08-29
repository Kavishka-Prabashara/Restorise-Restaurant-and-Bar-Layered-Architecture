package lk.ijse.mrGreen.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FertilizerTm {
    private String id;
    private String name;
    private String company;
    private double unit;
    private int qty;
    private String supId;
    private String lId;

}
