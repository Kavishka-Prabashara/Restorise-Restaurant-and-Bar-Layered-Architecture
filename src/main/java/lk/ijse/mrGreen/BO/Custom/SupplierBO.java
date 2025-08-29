package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(SupplierDto dto) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException ;

    boolean updateSupplier(SupplierDto dto) throws SQLException ;

    List<SupplierDto> loadAllSupplier() throws SQLException ;

    int getSupplierCount() throws SQLException ;

    String getSupplierName(String id) throws SQLException ;

    SupplierDto searchSupplier(String cusId) throws SQLException ;
}
