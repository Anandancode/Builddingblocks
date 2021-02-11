package com.buildingblocks.pojo;

public class MaterialInfoPojo {

    String MaterialItemName = "";
    String MaterialItemCost = "";
    String MaterialItemQty = "";
    String MaterialItemTax = "";
    String MaterialItemTotal = "";
    String MaterialCount = "";

    public String getMaterialCount() {
        return MaterialCount;
    }

    public void setMaterialCount(String materialCount) {
        MaterialCount = materialCount;
    }


    public String getMaterialItemName() {
        return MaterialItemName;
    }

    public void setMaterialItemName(String materialItemName) {
        MaterialItemName = materialItemName;
    }

    public String getMaterialItemCost() {
        return MaterialItemCost;
    }

    public void setMaterialItemCost(String materialItemCost) {
        MaterialItemCost = materialItemCost;
    }

    public String getMaterialItemQty() {
        return MaterialItemQty;
    }

    public void setMaterialItemQty(String materialItemQty) {
        MaterialItemQty = materialItemQty;
    }

    public String getMaterialItemTax() {
        return MaterialItemTax;
    }

    public void setMaterialItemTax(String materialItemTax) {
        MaterialItemTax = materialItemTax;
    }

    public String getMaterialItemTotal() {
        return MaterialItemTotal;
    }

    public void setMaterialItemTotal(String materialItemTotal) {
        MaterialItemTotal = materialItemTotal;
    }

}
