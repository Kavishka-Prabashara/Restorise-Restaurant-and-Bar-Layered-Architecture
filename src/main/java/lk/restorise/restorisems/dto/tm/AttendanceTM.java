package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;

public class AttendanceTM {
    private final SimpleIntegerProperty attendanceId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty employeeId = new SimpleIntegerProperty();
    private final SimpleStringProperty employeeName = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> attendanceDate = new SimpleObjectProperty<>();
    private final SimpleStringProperty cbAttendanceStatus = new SimpleStringProperty();

    public AttendanceTM(int attendanceId, int employeeId, String employeeName, Date attendanceDate, String cbAttendanceStatus) {
        setAttendanceId(attendanceId);
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setAttendanceDate(attendanceDate);
        setCbAttendanceStatus(cbAttendanceStatus);
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId.set(attendanceId);
    }

    public int getAttendanceId() {
        return attendanceId.get();
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    public int getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate.set(attendanceDate);
    }

    public Date getAttendanceDate() {
        return attendanceDate.get();
    }

    public void setCbAttendanceStatus(String cbAttendanceStatus) {
        this.cbAttendanceStatus.set(cbAttendanceStatus);
    }

    public String getCbAttendanceStatus() {
        return cbAttendanceStatus.get();
    }

}
