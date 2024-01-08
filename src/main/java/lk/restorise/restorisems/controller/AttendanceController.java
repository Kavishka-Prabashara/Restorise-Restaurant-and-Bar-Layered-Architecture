package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.dto.AttendanceDTO;
import lk.restorise.restorisems.dto.EmployeeDTO;
import lk.restorise.restorisems.dto.tm.AttendanceTM;
import lk.restorise.restorisems.model.AttendanceModel;
import lombok.Getter;

import java.sql.SQLException;

public class AttendanceController {

    @FXML
    private DatePicker attendanceDatePicker;

    @FXML
    private JFXButton btnattendanceClear;

    @FXML
    private JFXButton btnattendanceDelete;

    @FXML
    private JFXButton btnattendanceSave;

    @FXML
    private JFXButton btnattendanceUpdate;

    @FXML
    private ComboBox<String> cbAttendanceStatus;

    @FXML
    private ComboBox<Integer> cbEmployeeId;

    @FXML
    private TableColumn<AttendanceTM, Integer> colEmployeeID;

    @FXML
    private TableColumn<AttendanceTM, String> colEmployeeName;

    @FXML
    private TableColumn<AttendanceTM, java.sql.Date> colEmployeeDate;

    @Getter
    @FXML
    private TableColumn<AttendanceTM, String> colEmployeeStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<AttendanceTM> tblemployee2;

    @FXML
    private TextField txtEmployeeName;

    private AttendanceDTO selectedAttendance;

    @FXML
    void initialize() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmployeeStatus.setCellValueFactory(new PropertyValueFactory<>("cbAttendanceStatus"));
        colEmployeeDate.setCellValueFactory(new PropertyValueFactory<>("attendanceDate"));

        tblemployee2.setOnMouseClicked(this::handleTableClick);

        loadAttendanceTable();
    }

    private void handleTableClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            Object selectedItem = tblemployee2.getSelectionModel().getSelectedItem();

            if (selectedItem instanceof AttendanceDTO) {
                selectedAttendance = (AttendanceDTO) selectedItem;
                updateUI(selectedAttendance);
            }
        }
    }

    private final AttendanceModel attendanceModel = new AttendanceModel();

    @FXML
    void btnattendanceClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnattendanceDeleteOnAction(ActionEvent event) {
        // Implement delete logic
    }

    @FXML
    void btnattendanceSaveOnAction(ActionEvent event) {
        saveAttendance();
    }

    @FXML
    void btnattendanceUpdateOnAction(ActionEvent event) {
    }

    private void saveAttendance() {
        int employeeId = cbEmployeeId.getValue();
        String employeeName = txtEmployeeName.getText();
        java.sql.Date attendanceDate = java.sql.Date.valueOf(attendanceDatePicker.getValue());
        String status = cbAttendanceStatus.getValue();

        AttendanceDTO dto = new AttendanceDTO(employeeId, employeeName, status, attendanceDate);

        try {
            boolean isSaved = attendanceModel.saveAttendance(dto);

            if (isSaved) {
                // Show a confirmation alert
                // Optionally, you can update the table here
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance saved!").show();
                clearFields();
                loadAttendanceTable(); // Reload the table after saving
            } else {
                // Show an error alert
                new Alert(Alert.AlertType.ERROR, "Failed to save attendance!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        cbEmployeeId.setValue(null);
        txtEmployeeName.clear();

        cbAttendanceStatus.setValue(null);
        attendanceDatePicker.setValue(null);
    }

    private void loadAttendanceTable() {
        try {
            // Fetch all attendance records and update the table
            ObservableList<AttendanceTM> attendanceList = attendanceModel.getAllAttendancesForTableWithEmployeeIds();

            // Modify the Employee Name based on your requirements
            for (AttendanceTM attendanceTM : attendanceList) {
                int employeeId = attendanceTM.getEmployeeId();
                String employeeName = attendanceModel.getEmployeeName(employeeId);
                attendanceTM.setEmployeeName(employeeName);
            }

            // Set the items in the table
            tblemployee2.setItems(attendanceList);

            // Populate the employee IDs ComboBox
            loadEmployeeIds();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    private void loadEmployeeIds() {
        try {
            ObservableList<Integer> employeeIds = attendanceModel.getAllEmployeeIds();
            cbEmployeeId.setItems(employeeIds);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void updateEmployeeName(int employeeId) {
        try {
            String employeeName = attendanceModel.getEmployeeName(employeeId);
            txtEmployeeName.setText(employeeName);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void updateUI(AttendanceDTO attendance) {
        cbEmployeeId.setValue(attendance.getEmployeeId());
        txtEmployeeName.setText(attendance.getEmployeeName());
        cbAttendanceStatus.setValue(attendance.getCbAttendanceStatus());
        attendanceDatePicker.setValue(attendance.getAttendanceDate().toLocalDate());
    }


}
