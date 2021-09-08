package com.example.MyDiary;

public class DiaryData {
    private int iv_profile;
    private String tv_dytitle;
    private String tv_dycontent;

    public DiaryData(int iv_profile, String tv_dytitle, String tv_dycontent) {
        this.iv_profile = iv_profile;
        this.tv_dytitle = tv_dytitle;
        this.tv_dycontent = tv_dycontent;
    }

    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_dytitle() {
        return tv_dytitle;
    }

    public void setTv_dytitle(String tv_dytitle) {
        this.tv_dytitle = tv_dytitle;
    }

    public String getTv_dycontent() {
        return tv_dycontent;
    }

    public void setTv_dycontent(String tv_dycontent) {
        this.tv_dycontent = tv_dycontent;
    }
}
