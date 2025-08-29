package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.EmployeeDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Employee;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(Employee dto) throws SQLException {

        Object object = search(dto.getId());

        if(object!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                    dto.getId(),dto.getName(),dto.getAge(),dto.getAddress(),dto.getJob(),dto.getSalary());
        }

    }

    public boolean delete(String id) throws SQLException {

        return sqlUtil.execute("DELETE FROM employee WHERE emp_id = ?",id);
    }

    public boolean update(Employee dto) throws SQLException {

        return sqlUtil.execute("UPDATE employee SET name = ?, age = ?, address = ?, job_role = ?, salary = ? WHERE emp_id = ?"
                ,dto.getName(),dto.getAge(),dto.getAddress(),dto.getJob(),dto.getSalary(),dto.getId());
    }

    public List<Employee> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> dto= new ArrayList<>();

        while (resultSet.next()){
            dto.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)
            ));
        }
        return dto;


    }

    public int getCount() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT COUNT(*) AS num_Employee FROM employee");

        resultSet.next();

        int count = resultSet.getInt("num_Employee");

        return count;
    }

    public String getName(String id) throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT name FROM employee WHERE emp_id = ?",id);

        resultSet.next();

        String name = resultSet.getString("name");

        return name;
    }

    public Employee search(String id) throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM employee WHERE emp_id = ?",id);

        Employee dto = null;

        if(resultSet.next()){
            dto = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)
            );
        }
        return dto;
    }
}
