package lk.restorise.restorisems.model;

import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static boolean saveEmployee(EmployeeDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO employeedetails VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, dto.getTxtEmpId());
            pstm.setString(2, dto.getTxtEmpName());
            pstm.setInt(3, dto.getTxtEmpMobile());
            pstm.setString(4, dto.getTxtEmpemail());
            pstm.setDate(5, Date.valueOf(dto.getEmpDate()));
            pstm.setString(6, dto.gettxtemployeequestion());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean updateEmployee(EmployeeDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE employeedetails SET NAME=?, MOBILE_NO=?, EMAIL=?, DATE_OF_BIRTH=?, JOB_POST=? WHERE EMPLOYEE_ID=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getTxtEmpName());
            pstm.setInt(2, dto.getTxtEmpMobile());
            pstm.setString(3, dto.getTxtEmpemail());
            pstm.setDate(4, Date.valueOf(dto.getEmpDate()));
            pstm.setString(5, dto.getTxtemployeequestion()); // Updated column name
            pstm.setInt(6, dto.getTxtEmpId());
            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean deleteEmployees(int employeeId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM employeedetails WHERE EMPLOYEE_ID=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, employeeId);
            return pstm.executeUpdate() > 0;
        }
    }

    public static List<EmployeeDTO> getAllEmployees() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employeedetails"; // Corrected table name
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();

            List<EmployeeDTO> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                EmployeeDTO dto = new EmployeeDTO(
                        resultSet.getInt("EMPLOYEE_ID"),
                        resultSet.getString("NAME"), // Corrected column name
                        resultSet.getInt("MOBILE_NO"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("JOB_POST"),
                        resultSet.getDate("DATE_OF_BIRTH").toLocalDate()
                );
                employeeList.add(dto);
            }
            return employeeList;
        }
    }
}
