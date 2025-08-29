package lk.ijse.mrGreen.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.mrGreen.BO.BOFactory;
import lk.ijse.mrGreen.BO.Custom.Impl.CustomerBOImpl;
import lk.ijse.mrGreen.BO.Custom.Impl.EmployeeBOImpl;
import lk.ijse.mrGreen.BO.Custom.Impl.SupplierBOImpl;
import lk.ijse.mrGreen.dto.CustomerDto;
import lk.ijse.mrGreen.dto.EmployeeDto;
import lk.ijse.mrGreen.dto.SupplierDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static net.sf.jasperreports.view.JasperViewer.viewReport;

public class ReportFormController {
    @FXML
    public JFXComboBox cmbCustomer;
    public JFXComboBox cmbEmpId;
    public JFXComboBox cmbSuppId;
    @FXML
    private AnchorPane Anchor;

    private CustomerBOImpl customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CUSTOMER);
    private EmployeeBOImpl employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.EMPLOYEE);
    private SupplierBOImpl supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.SUPPLIER);

    public void initialize(){
        loadAllCustomer();
        loadAllEmployee();
        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = supplierBO.loadAllSupplier();
            for (SupplierDto dto: dtoList) {
                obList.add(dto.getSup_id());
            }
            cmbSuppId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllEmployee() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = employeeBO.loadAllEmployee();
            for (EmployeeDto dto: dtoList) {
                obList.add(dto.getId());
            }
            cmbEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllCustomer() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customerBO.loadAllCustomer();
            for (CustomerDto  dto: dtoList) {
                obList.add(dto.getId());
            }
            cmbCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void CustomerReportOnAction(ActionEvent event) throws JRException, SQLException {

        String cus_id= (String) cmbCustomer.getValue();

        if (cus_id==null) {
            new Alert(Alert.AlertType.ERROR,"Customer id is empty").show();
            return;
        }

        try {
            CustomerDto dto = customerBO.searchCustomer(cus_id);
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getTel());
            if (dto !=null) {
                viewReport(dto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void viewReport(CustomerDto dto) throws JRException, SQLException {
        HashMap hashMap = new HashMap();
        hashMap.put("id",dto.getId());
        hashMap.put("name",dto.getName());
        hashMap.put("address",dto.getAddress());
        hashMap.put("tel",dto.getTel());


        InputStream resourceAsStream =  getClass().getResourceAsStream("/reports/CustomerDetils.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport= JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                hashMap,
                new JREmptyDataSource()
        );

        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void backOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Stage stage = (Stage) Anchor.getScene().getWindow();

        Scene scene=new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    public void EmployeeReportOnAction(ActionEvent actionEvent) {

        String empId= (String) cmbEmpId.getValue();

        if(empId==null){
            new Alert(Alert.AlertType.ERROR,"Employee id is empty").show();
            return;
        }

        try {
            EmployeeDto dto = employeeBO.searchEmployee(empId);
            if (dto!=null) {
                viewEmployeeReport(dto);
            }
        } catch (SQLException | JRException e) {
            throw new RuntimeException(e);
        }


    }

    private void viewEmployeeReport(EmployeeDto dto) throws JRException {
        HashMap hashMap = new HashMap();
        hashMap.put("name",dto.getName());
        hashMap.put("age",Integer.toString(dto.getAge()));
        hashMap.put("address",dto.getAddress());
        hashMap.put("job role",dto.getJob());
        hashMap.put("Salary",Double.toString(dto.getSalary()));


        InputStream resourceAsStream =  getClass().getResourceAsStream("/reports/Employee_details.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport= JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                hashMap,
                new JREmptyDataSource()
        );

        JasperViewer.viewReport(jasperPrint,false);

    }

    public void supplierReportOnAction(ActionEvent actionEvent) {
        String suppId = (String) cmbSuppId.getValue();
        if (suppId==null) {
            new Alert(Alert.AlertType.ERROR,"Supplier id is empty").show();
            return;
        }

        try {
            SupplierDto dto = supplierBO.searchSupplier(suppId);
            if(dto!=null){
                viewSupplierReport(dto);
            }
        } catch (SQLException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewSupplierReport(SupplierDto dto) throws JRException {
        HashMap hashMap = new HashMap();
        hashMap.put("id",dto.getSup_id());
        hashMap.put("name",dto.getName());
        hashMap.put("company",dto.getCompany());
        hashMap.put("tel",dto.getTel());



        InputStream resourceAsStream =  getClass().getResourceAsStream("/reports/Supplier_details.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport= JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                hashMap,
                new JREmptyDataSource()
        );

        JasperViewer.viewReport(jasperPrint,false);

    }

    public void empIdOnAction(ActionEvent event) {
        EmployeeReportOnAction(event);
    }

    public void suppIdOnAction(ActionEvent event) {
        supplierReportOnAction(event);
    }

    public void cusIdOnAction(ActionEvent event) throws JRException, SQLException {
        CustomerReportOnAction(event);
    }
}
