package model;


public class RequestDB {
    private int id;
    private String emp_name;
    private String lr_date;
    private String reason;
    private String status = "False";

    public RequestDB(String emp_name, String lr_date, String reason, String status ) {
        this.emp_name = emp_name;
        this.lr_date = lr_date;
        this.reason = reason;
        this.status = status;
    }

    public RequestDB(int id, String emp_name, String lr_date, String reason, String status) {
        this.id = id;
        this.emp_name = emp_name;
        this.lr_date = lr_date;
        this.reason = reason;
        this.status = status;
    }

    public RequestDB(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getLr_date() {
        return lr_date;
    }

    public void setLr_date(String lr_date) {
        this.lr_date = lr_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

