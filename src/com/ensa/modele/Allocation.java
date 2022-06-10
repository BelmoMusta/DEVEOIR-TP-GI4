package com.ensa.modele;

import java.sql.Date;

public class Allocation {
    private String productid;
    private String userid;
    private Date allocationdate;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getAllocationdate() {
        return allocationdate;
    }

    public void setAllocationdate(Date allocationdate) {
        this.allocationdate = allocationdate;
    }
}
