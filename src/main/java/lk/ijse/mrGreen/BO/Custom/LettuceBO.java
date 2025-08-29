package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.LettuceDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface LettuceBO extends SuperBO {
    boolean saveLettuce(LettuceDto dto) throws SQLException;

    boolean deleteLettuce(String id) throws SQLException ;

    boolean updateLettuce(LettuceDto dto) throws SQLException ;

    List<LettuceDto> loadAllLettuce() throws SQLException ;

    int getLettuceCount() throws SQLException ;

    String getLettuceName(String id) throws SQLException ;

    LettuceDto searchLettuce(String cusId) throws SQLException ;

    boolean updateLettuceQty(List<CartTm> cartTmList) throws SQLException ;
}
