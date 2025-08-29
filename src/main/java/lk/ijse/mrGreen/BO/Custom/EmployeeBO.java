package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(EmployeeDto dto) throws SQLException;

    boolean deleteEmployee(String id) throws SQLException ;

    boolean updateEmployee(EmployeeDto dto) throws SQLException ;

    List<EmployeeDto> loadAllEmployee() throws SQLException ;

    int getEmployeeCount() throws SQLException ;

    String getEmployeeName(String id) throws SQLException ;

    EmployeeDto searchEmployee(String cusId) throws SQLException ;
}
