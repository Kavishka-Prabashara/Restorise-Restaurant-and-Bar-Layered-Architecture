package lk.restorise.restorisems.dto;

import lombok.Getter;

import java.time.LocalDate;

public class EmployeeDTO {
    private LocalDate empDate;
    private int txtEmpId;
    private String txtEmpemail;
    @Getter
    private String txtEmpName;
    private int txtEmpMobile;
    private String txtemployeequestion;

    public EmployeeDTO(int employeeId, String employeeName, int employeMobile, String employeEmail, String employeeCbJOPOST, LocalDate employeeDob) {
    this.txtEmpId=employeeId;
    this.txtEmpName=employeeName;
    this.txtEmpMobile=employeMobile;
    this.txtEmpemail=employeEmail;
    this.txtemployeequestion=employeeCbJOPOST;
    this.empDate=employeeDob;

    }
    // ... (unchanged code)

    @Override
    public String toString() {
        return "{" +
                "txtEmpId" + txtEmpId +
                "txtEmpName" + txtEmpName +
                "txtEmpemail" + txtEmpemail +
                "txtEmpMobile" + txtEmpMobile +
                "cbemployeequestion" + txtemployeequestion +
                "empDate" + empDate +
                "}";
    }

    public int getTxtEmpId() {
        return txtEmpId;
    }

    public void setTxtEmpId(int txtEmpId) {
        this.txtEmpId = txtEmpId;
    }

    public void setTxtEmpName(String txtEmpName) {
        this.txtEmpName = txtEmpName;
    }

    public int getTxtEmpMobile() {
        return txtEmpMobile;
    }

    public void setTxtEmpMobile(int txtEmpMobile) {
        this.txtEmpMobile = txtEmpMobile;
    }

    public String getTxtEmpemail() {
        return txtEmpemail;
    }

    public void setTxtEmpemail(String txtEmpemail) {
        this.txtEmpemail = txtEmpemail;
    }



    public void settxtemployeequestion(String txtemployeequestion) {
        this.txtemployeequestion = txtemployeequestion;
    }

    public LocalDate getEmpDate() {
        return empDate;
    }

    public void setEmpDate(LocalDate empDate) {
        this.empDate = empDate;
    }
    



    public String getTxtemployeequestion() {
        return txtemployeequestion;
    }

    public void setTxtemployeequestion(String txtemployeequestion) {
        this.txtemployeequestion = txtemployeequestion;
    }

    public String gettxtemployeequestion() {
        return txtemployeequestion;
    }


    // ... (unchanged code)
}
