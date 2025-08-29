package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.GreenHouseDto;

import java.sql.SQLException;
import java.util.List;

public interface GreenhouseBO extends SuperBO {
    boolean saveGreenhouse(GreenHouseDto dto) throws SQLException;

    boolean deleteGreenhouse(String id) throws SQLException ;

    boolean updateGreenhouse(GreenHouseDto dto) throws SQLException ;

    List<GreenHouseDto> loadAllGreenhouse() throws SQLException ;

    int getGreenhouseCount() throws SQLException ;

    String getGreenhouseName(String id) throws SQLException ;

    GreenHouseDto searchGreenhouse(String cusId) throws SQLException ;
}
