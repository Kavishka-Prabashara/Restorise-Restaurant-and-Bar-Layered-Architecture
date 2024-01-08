package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.dto.EmployeeDTO;
import lk.restorise.restorisems.model.EmployeeModel;
import lk.restorise.restorisems.model.TableModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class EmployeeController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtemployeequestion;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeBirth;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeEmail;

    @FXML
    private TableColumn<EmployeeDTO, Integer> colEmployeeId;

    @FXML
    private TableColumn<EmployeeDTO, Integer> colEmployeeMobileNO;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeName;

    @FXML
    private TableColumn<EmployeeDTO, String> colJobPost;

    @FXML
    private DatePicker empDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpMobile;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpemail;

    private ObservableList<EmployeeDTO> employeeData = FXCollections.observableArrayList();

    private EmployeeDTO selectedEmployee;


    @FXML
    void EmpSaveOnAction(ActionEvent event) {
        int employees_ID = Integer.parseInt(txtEmpId.getText());
        String employee_NAME = txtEmpName.getText();
        int employee_MOBILE = Integer.parseInt(txtEmpMobile.getText());
        String employee_EMAIL = txtEmpemail.getText();
        String employee_JOBpost = txtemployeequestion.getText();
        LocalDate employee_DOB = empDate.getValue();

        var dto = new EmployeeDTO(employees_ID, employee_NAME, employee_MOBILE, employee_EMAIL, employee_JOBpost, employee_DOB);

        try {
            boolean isSaved = EmployeeModel.saveEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clearFields();
                loadEmployees();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadEmployees() {
        try {
            tblEmployee.setItems(FXCollections.observableArrayList(EmployeeModel.getAllEmployees()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

 
    }

    @FXML
    void EmpUpdateOnAction(ActionEvent event) {
        if (selectedEmployee != null) {
            EmployeeDTO updatedDTO = getEmployeeDTOFromUI();
            updatedDTO.setTxtEmpId(Integer.parseInt(String.valueOf(Integer.parseInt(txtEmpId.getText()))));

            try {
                boolean isUpdated = EmployeeModel.updateEmployee(updatedDTO);
                if (isUpdated) {
                    int selectedIndex = employeeData.indexOf(selectedEmployee);
                    employeeData.set(selectedIndex, updatedDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select an employee to update.").show();
        }
    }

    @FXML
    void DeleteEmployeeOnAction(ActionEvent event) {
        EmployeeDTO selectedDTO = tblEmployee.getSelectionModel().getSelectedItem();
        if (selectedDTO != null) {
            Optional<ButtonType> result = showConfirmationDialog("Delete Confirmation", "Are you sure you want to delete?");

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = EmployeeModel.deleteEmployees(selectedDTO.getTxtEmpId());
                    if (isDeleted) {
                        employeeData.remove(selectedDTO);
                        new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                        clearFields();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select an Employee to delete.").show();
        }
    }

    private Optional<ButtonType> showConfirmationDialog(String title, String content) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText(content);

        return confirmationDialog.showAndWait();
    }

    private EmployeeDTO getEmployeeDTOFromUI() {
        int employee_ID = Integer.parseInt(txtEmpId.getText());
        String employee_NAME = txtEmpName.getText();
        int employe_MOBILE = Integer.parseInt(txtEmpMobile.getText());
        String employe_EMAIL = txtEmpemail.getText();
        String employee_cbJOPOST = txtemployeequestion.getText();
        LocalDate employee_DOB = empDate.getValue();

        return new EmployeeDTO(employee_ID, employee_NAME, employe_MOBILE, employe_EMAIL, employee_cbJOPOST, employee_DOB);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmpId.clear();
        txtEmpName.clear();
        txtEmpMobile.clear();
        txtEmpemail.clear();
        txtemployeequestion.clear();
        empDate.setValue(null);
    }

    @FXML
    void initialize() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("txtEmpId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("txtEmpName"));
        colEmployeeMobileNO.setCellValueFactory(new PropertyValueFactory<>("txtEmpMobile"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("txtEmpemail"));
        colJobPost.setCellValueFactory(new PropertyValueFactory<>("txtemployeequestion"));
        colEmployeeBirth.setCellValueFactory(new PropertyValueFactory<>("empDate"));



        tblEmployee.setOnMouseClicked(this::handleTableClick);

        loadEmployees();

    }

    private void handleTableClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            Object selectedItem = tblEmployee.getSelectionModel().getSelectedItem();

            if (selectedItem instanceof EmployeeDTO) {
                selectedEmployee = (EmployeeDTO) selectedItem;
                updateUI(selectedEmployee);
            }
        }
    }

    private void updateUI(EmployeeDTO employee) {
        txtEmpId.setText(String.valueOf(employee.getTxtEmpId()));
        txtEmpName.setText(employee.getTxtEmpName());
        txtEmpMobile.setText(String.valueOf(employee.getTxtEmpMobile()));
        txtEmpemail.setText(employee.getTxtEmpemail());
        txtemployeequestion.setText(employee.getTxtemployeequestion());
        empDate.setValue(employee.getEmpDate());
    }


}

