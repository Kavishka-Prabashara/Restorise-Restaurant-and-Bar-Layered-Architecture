package lk.restorise.restorisems.dto;

import lombok.*;
import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class AttendanceDTO {
    private int employeeId;
    private String employeeName;
    private Date attendanceDate;
    private String cbAttendanceStatus;

    public AttendanceDTO(int employeeId, String employeeName, String cbAttendanceStatus, Date attendanceDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.cbAttendanceStatus = cbAttendanceStatus;
        this.attendanceDate=attendanceDate;
    }
}
