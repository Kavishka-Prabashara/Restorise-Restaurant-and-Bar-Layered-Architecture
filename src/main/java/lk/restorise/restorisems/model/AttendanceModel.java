package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.AttendanceDTO;
import lk.restorise.restorisems.dto.tm.AttendanceTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {
    public boolean saveAttendance(AttendanceDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO attendancedetails (EMPLOYEE_ID, EMPLOYEE_NAME, ATTENDANCE_DATE, STATUS) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, dto.getEmployeeId());
            pstm.setString(2, dto.getEmployeeName());
            pstm.setDate(3, dto.getAttendanceDate());
            pstm.setString(4, dto.getCbAttendanceStatus());

            return pstm.executeUpdate() > 0;
        }
    }
    public ObservableList<AttendanceTM> getAllAttendancesForTableWithEmployeeIds() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendancedetails";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            ObservableList<AttendanceTM> attendanceList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                AttendanceTM tm = new AttendanceTM(
                        resultSet.getInt("ATTENDANCE_ID"),
                        resultSet.getInt("EMPLOYEE_ID"),
                        resultSet.getString("EMPLOYEE_NAME"),
                        resultSet.getDate("ATTENDANCE_DATE"),
                        resultSet.getString("STATUS")
                );
                attendanceList.add(tm);}
            return attendanceList;
        }
    }
    public ObservableList<Integer> getAllEmployeeIds() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT EMPLOYEE_ID FROM employeedetails";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            ObservableList<Integer> employeeIds = FXCollections.observableArrayList();

            while (resultSet.next()) {
                employeeIds.add(resultSet.getInt("EMPLOYEE_ID"));
            }
            return employeeIds;
        }
    }
    public String getEmployeeName(int employeeId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT NAME FROM employeedetails WHERE EMPLOYEE_ID = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, employeeId);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("NAME");
                }
            }
        }
        return null;
    }
}




