package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.EmployeeBO;
import lk.ijse.mrGreen.DAO.Custom.EmployeeDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Employee;
import lk.ijse.mrGreen.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.EMPLOYEE);
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getAge(),dto.getAddress(),dto.getJob(),dto.getSalary()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getAge(),dto.getAddress(),dto.getJob(),dto.getSalary()));
    }

    @Override
    public List<EmployeeDto> loadAllEmployee() throws SQLException {
        List<Employee> employee = employeeDAO.loadAll();
        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        for (Employee dto: employee) {
            dtoList.add(new EmployeeDto(dto.getId(),dto.getName(),dto.getAge(),dto.getAddress(),dto.getJob(),dto.getSalary()));
        }
        return dtoList;
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeeDAO.getCount();
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getName(id);
    }

    @Override
    public EmployeeDto searchEmployee(String cusId) throws SQLException {
        Employee employee = employeeDAO.search(cusId);

        return new EmployeeDto(employee.getId(),employee.getName(),employee.getAge(),employee.getAddress(),employee.getJob(),employee.getSalary());
    }
}
