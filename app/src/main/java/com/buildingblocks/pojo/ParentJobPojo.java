package com.buildingblocks.pojo;

import java.util.ArrayList;

public class ParentJobPojo {

    String Parentdescription = "";
    String ParentJobTitle = "";
    ArrayList<MaterialInfoPojo> materialPojo = null;

    public String getParentdescription() {
        return Parentdescription;
    }

    public void setParentdescription(String parentdescription) {
        Parentdescription = parentdescription;
    }

    public String getParentJobTitle() {
        return ParentJobTitle;
    }

    public void setParentJobTitle(String parentJobTitle) {
        ParentJobTitle = parentJobTitle;
    }

    public ArrayList<MaterialInfoPojo> getMaterialPojo() {
        return materialPojo;
    }

    public void setMaterialPojo(ArrayList<MaterialInfoPojo> materialPojo) {
        this.materialPojo = materialPojo;
    }


}
