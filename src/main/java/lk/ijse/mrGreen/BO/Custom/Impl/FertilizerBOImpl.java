package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.FertilizerBO;
import lk.ijse.mrGreen.DAO.Custom.FertilizerDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Fertilizer;
import lk.ijse.mrGreen.dto.Fertilizerdto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerBOImpl implements FertilizerBO {

    private FertilizerDAO fertilizerDAO = (FertilizerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.FERTILIZER);

    @Override
    public boolean saveFertilizer(Fertilizerdto dto) throws SQLException {
        return fertilizerDAO.save(new Fertilizer(dto.getId(),dto.getName(),dto.getCompany(),dto.getUnit()
                ,dto.getQty(),dto.getSupId(),dto.getLId()));
    }

    @Override
    public boolean deleteFertilizer(String id) throws SQLException {
        return fertilizerDAO.delete(id);
    }

    @Override
    public boolean updateFertilizer(Fertilizerdto dto) throws SQLException {
        return fertilizerDAO.update(new Fertilizer(dto.getId(),dto.getName(),dto.getCompany(),dto.getUnit()
                ,dto.getQty(),dto.getSupId(),dto.getLId()));
    }

    @Override
    public List<Fertilizerdto> loadAllFertilizer() throws SQLException {
        List<Fertilizer> fertilizer = fertilizerDAO.loadAll();
        ArrayList<Fertilizerdto> dtoList =  new ArrayList<>();

        for (Fertilizer dto: fertilizer) {
            dtoList.add(new Fertilizerdto(dto.getId(),dto.getName(),dto.getCompany(),dto.getUnit()
                    ,dto.getQty(),dto.getSupId(),dto.getLId()));
        }
        return dtoList;
    }

    @Override
    public int getFertilizerCount() throws SQLException {
        return 0;
    }

    @Override
    public String getFertilizerName(String id) throws SQLException {
        return null;
    }

    @Override
    public Fertilizerdto searchFertilizer(String cusId) throws SQLException {
        Fertilizer fertilizer = fertilizerDAO.search(cusId);

        return new Fertilizerdto(fertilizer.getId(),fertilizer.getName(),fertilizer.getCompany(),fertilizer.getUnit()
                ,fertilizer.getQty(),fertilizer.getSupId(),fertilizer.getLId());
    }
}
