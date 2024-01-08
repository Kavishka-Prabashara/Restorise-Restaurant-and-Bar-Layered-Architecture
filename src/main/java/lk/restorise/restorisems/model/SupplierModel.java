package lk.restorise.restorisems.model;

import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static boolean saveSupplier(SupplierDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO supplierdetails (SUPPLIER_ID, NAME, MOBILE_NO, EMAIL, ITEM_ID, SUPPLY_ITEM, SUPPLY_DATE, SUPPLY_ITEMSTOCKQTY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getTxtSupplierID());
            pstm.setString(2, dto.getTxtSupplierName());
            pstm.setInt(3, dto.getTxtEmpMobile());
            pstm.setString(4, dto.getTxtSupplierEmail());
            pstm.setString(5, dto.getTxtSuppyItemID());
            pstm.setString(6, dto.getCbSupplyItem());
            pstm.setDate(7, java.sql.Date.valueOf(dto.getDtSupplyingDate()));
            pstm.setInt(8, dto.getSuppliedStockQTY());
            return pstm.executeUpdate() > 0;
        }
    }



    public static boolean updateSupplier(SupplierDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE supplierdetails SET NAME=?, MOBILE_NO=?, EMAIL=?, ITEM_ID=?, SUPPLY_ITEM=?, SUPPLY_DATE=?, SUPPLY_ITEMSTOCKQTY=? WHERE SUPPLIER_ID=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getTxtSupplierName());
            pstm.setInt(2, dto.getTxtEmpMobile());
            pstm.setString(3, dto.getTxtSupplierEmail());
            pstm.setString(4, dto.getTxtSuppyItemID());
            pstm.setString(5, dto.getCbSupplyItem());
            pstm.setDate(6, java.sql.Date.valueOf(dto.getDtSupplyingDate()));
            pstm.setInt(7, dto.getSuppliedStockQTY());
            pstm.setString(8, dto.getTxtSupplierID());
            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean deleteSupplier(String supplierId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM supplierdetails WHERE SUPPLIER_ID=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, supplierId);
            return pstm.executeUpdate() > 0;
        }
    }

    public static List<SupplierDTO> getAllSuppliers() throws SQLException {



        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplierdetails";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<SupplierDTO> supplierList = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDTO dto = new SupplierDTO(
                    resultSet.getString("SUPPLIER_ID"),
                    resultSet.getString("NAME"),
                    resultSet.getInt("MOBILE_NO"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("ITEM_ID"),
                    resultSet.getString("SUPPLY_ITEM"),
                    resultSet.getDate("SUPPLY_DATE").toLocalDate(),
                    resultSet.getInt("SUPPLY_ITEMSTOCKQTY") // Use the correct column name here
            );
            supplierList.add(dto);
        }
        return supplierList;
    }
}
