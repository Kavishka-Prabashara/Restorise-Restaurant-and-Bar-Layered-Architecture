package lk.ijse.mrGreen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String id;
    private String name;
    private int age;
    private String address;
    private String job;
    private double salary;
}
