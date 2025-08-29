package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.SupplierBO;
import lk.ijse.mrGreen.DAO.Custom.SupplierDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Supplier;
import lk.ijse.mrGreen.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    private SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getSup_id(),dto.getName(),dto.getCompany()
                ,dto.getTel(),dto.getUser_id()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSup_id(),dto.getName(),dto.getCompany()
                ,dto.getTel(),dto.getUser_id()));
    }

    @Override
    public List<SupplierDto> loadAllSupplier() throws SQLException {
        List<Supplier> supplier = supplierDAO.loadAll();
        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        for (Supplier dto: supplier) {
            dtoList.add(new SupplierDto(dto.getSup_id(),dto.getName(),dto.getCompany()
                    ,dto.getTel(),dto.getUser_id()));
        }
        return dtoList;
    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getCount();
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        return supplierDAO.getName(id);
    }

    @Override
    public SupplierDto searchSupplier(String cusId) throws SQLException {
        Supplier supplier = supplierDAO.search(cusId);

        return new SupplierDto(supplier.getSup_id(),supplier.getName(),supplier.getCompany()
                ,supplier.getTel(),supplier.getUser_id());
    }
}
