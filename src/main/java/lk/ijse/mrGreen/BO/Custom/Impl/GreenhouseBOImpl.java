package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.GreenhouseBO;
import lk.ijse.mrGreen.DAO.Custom.GreenHouseDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Greenhouse;
import lk.ijse.mrGreen.dto.GreenHouseDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreenhouseBOImpl implements GreenhouseBO {

    private GreenHouseDAO greenHouseDAO = (GreenHouseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.GREENHOUSE);
    @Override
    public boolean saveGreenhouse(GreenHouseDto dto) throws SQLException {
        return greenHouseDAO.save(new Greenhouse(dto.getId(),dto.getName(),dto.getL_id(),dto.getSeed(),dto.getTemp(),dto.getPh()));
    }

    @Override
    public boolean deleteGreenhouse(String id) throws SQLException {
        return greenHouseDAO.delete(id);
    }

    @Override
    public boolean updateGreenhouse(GreenHouseDto dto) throws SQLException {
        return greenHouseDAO.update(new Greenhouse(dto.getId(),dto.getName(),dto.getL_id(),dto.getSeed(),dto.getTemp(),dto.getPh()));
    }

    @Override
    public List<GreenHouseDto> loadAllGreenhouse() throws SQLException {
        List<Greenhouse> greenhouse = greenHouseDAO.loadAll();
        ArrayList<GreenHouseDto> dtoList = new ArrayList<>();

        for (Greenhouse dto: greenhouse) {
            dtoList.add(new GreenHouseDto(dto.getId(),dto.getName(),dto.getL_id(),dto.getSeed(),dto.getTemp(),dto.getPh()));
        }
        return dtoList;
    }

    @Override
    public int getGreenhouseCount() throws SQLException {
        return greenHouseDAO.getCount();
    }

    @Override
    public String getGreenhouseName(String id) throws SQLException {
        return greenHouseDAO.getName(id);
    }

    @Override
    public GreenHouseDto searchGreenhouse(String cusId) throws SQLException {
        Greenhouse greenhouse = greenHouseDAO.search(cusId);

        return new GreenHouseDto(greenhouse.getId(),greenhouse.getName(),greenhouse.getL_id(),greenhouse.getSeed(),greenhouse.getTemp(),greenhouse.getPh());
    }
}
