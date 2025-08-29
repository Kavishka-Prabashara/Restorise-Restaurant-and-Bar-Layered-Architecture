package lk.ijse.mrGreen.DAO.Custom;

import lk.ijse.mrGreen.DAO.CrudDAO;
import lk.ijse.mrGreen.Entity.Lettuce;
import lk.ijse.mrGreen.dto.LettuceDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.*;
import java.util.List;

public interface LettuceDAO extends CrudDAO<Lettuce> {
//    boolean saveLettuce(LettuceDto dto) throws SQLException ;
//
//    List<LettuceDto> getAllLettuceDetails() throws SQLException ;
//
//    boolean deleteLettuce(String id) throws SQLException ;
//
//    boolean updateLettuce(LettuceDto dto) throws SQLException ;
//
//    int getCount() throws SQLException ;
//
//    LettuceDto getLettueDetails(String id) throws SQLException ;
//
    boolean updateLettuceQty(List<CartTm> cartTmList) throws SQLException ;

}
