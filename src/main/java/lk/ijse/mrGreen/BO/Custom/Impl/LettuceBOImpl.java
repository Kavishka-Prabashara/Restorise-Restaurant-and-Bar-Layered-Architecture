package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.LettuceBO;
import lk.ijse.mrGreen.DAO.Custom.LettuceDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Lettuce;
import lk.ijse.mrGreen.dto.LettuceDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LettuceBOImpl implements LettuceBO {

    private LettuceDAO lettuceDAO = (LettuceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.LETTUCE);
    @Override
    public boolean saveLettuce(LettuceDto dto) throws SQLException {
        return lettuceDAO.save(new Lettuce(dto.getId(),dto.getName(),dto.getTemp(),dto.getHumid(),dto.getQty(),
                dto.getSeed(),dto.getUnit(),dto.getSuppId()));
    }

    @Override
    public boolean deleteLettuce(String id) throws SQLException {
        return lettuceDAO.delete(id);
    }

    @Override
    public boolean updateLettuce(LettuceDto dto) throws SQLException {
        return lettuceDAO.update(new Lettuce(dto.getId(),dto.getName(),dto.getTemp(),dto.getHumid(),dto.getQty(),
                dto.getSeed(),dto.getUnit(),dto.getSuppId()));
    }

    @Override
    public List<LettuceDto> loadAllLettuce() throws SQLException {
        List<Lettuce> lettuce = lettuceDAO.loadAll();
        ArrayList<LettuceDto> dtoList = new ArrayList<>();

        for (Lettuce dto: lettuce) {
            dtoList.add(new LettuceDto(dto.getId(),dto.getName(),dto.getTemp(),dto.getHumid(),dto.getQty(),
                    dto.getSeed(),dto.getUnit(),dto.getSuppId()));
        }
        return dtoList;
    }

    @Override
    public int getLettuceCount() throws SQLException {
        return lettuceDAO.getCount();
    }

    @Override
    public String getLettuceName(String id) throws SQLException {
        return lettuceDAO.getName(id);
    }

    @Override
    public LettuceDto searchLettuce(String cusId) throws SQLException {
        Lettuce lettuce = lettuceDAO.search(cusId);

        return new LettuceDto(lettuce.getId(),lettuce.getName(),lettuce.getTemp(),lettuce.getHumid(),lettuce.getQty(),
                lettuce.getSeed(),lettuce.getUnit(),lettuce.getSuppId());
    }

    @Override
    public boolean updateLettuceQty(List<CartTm> cartTmList) throws SQLException {
        return lettuceDAO.updateLettuceQty(cartTmList);
    }
}
