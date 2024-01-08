package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.TableDTO;
import lk.restorise.restorisems.dto.tm.TableTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableModel {
    public void insertTable(TableDTO tableDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO table_RestoRise (table_id, no_of_Chairs) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tableDTO.getTableID());
            preparedStatement.setInt(2, tableDTO.getNoOfChairs());

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public void updateTable(TableDTO tableDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "UPDATE table_RestoRise SET no_of_Chairs = ? WHERE table_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tableDTO.getNoOfChairs());
            preparedStatement.setInt(2, tableDTO.getTableID());

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public void deleteTable(int tableID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "DELETE FROM table_RestoRise WHERE table_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tableID);

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public ObservableList<TableTM> getAllTables() throws SQLException {
        ObservableList<TableTM> tablesList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM table_RestoRise";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int tableID = resultSet.getInt("table_id");
                int noOfChairs = resultSet.getInt("no_of_Chairs");

                TableTM tableTM = new TableTM(tableID, noOfChairs);
                tablesList.add(tableTM);
            }
        } finally {
            closeResources();
        }

        return tablesList;
    }

    private void closeResources() {

        }
    }

