package com.ecard.pojo.queryResult;

import java.util.List;

public class DepartmentQr {
    //private String departmentName;
    private List<DrInfoQr> drInfoQrs;

    /*public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }*/

    public List<DrInfoQr> getDrInfoQrs() {
        return drInfoQrs;
    }

    public void setDrInfoQrs(List<DrInfoQr> drInfoQrs) {
        this.drInfoQrs = drInfoQrs;
    }
}
