package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.Fertilizerdto;

import java.sql.SQLException;
import java.util.List;

public interface FertilizerBO extends SuperBO {
    boolean saveFertilizer(Fertilizerdto dto) throws SQLException;

    boolean deleteFertilizer(String id) throws SQLException ;

    boolean updateFertilizer(Fertilizerdto dto) throws SQLException ;

    List<Fertilizerdto> loadAllFertilizer() throws SQLException ;

    int getFertilizerCount() throws SQLException ;

    String getFertilizerName(String id) throws SQLException ;

    Fertilizerdto searchFertilizer(String cusId) throws SQLException ;
}
