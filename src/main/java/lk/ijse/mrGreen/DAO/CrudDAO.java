package lk.ijse.mrGreen.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T dto) throws SQLException;

    boolean delete(String id) throws SQLException ;

    boolean update(T dto) throws SQLException ;

    List<T> loadAll() throws SQLException ;

    int getCount() throws SQLException ;

    String getName(String id) throws SQLException ;

    T search(String cusId) throws SQLException ;
}
