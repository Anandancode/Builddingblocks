package com.buildingblocks.pojo;

public class JobInfoPojo {

    String JobTitleInfo = "";
    String TobTitleId = "";
    boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



    public String getJobTitleInfo() {
        return JobTitleInfo;
    }

    public void setJobTitleInfo(String jobTitleInfo) {
        JobTitleInfo = jobTitleInfo;
    }

    public String getTobTitleId() {
        return TobTitleId;
    }

    public void setTobTitleId(String tobTitleId) {
        TobTitleId = tobTitleId;
    }


}
