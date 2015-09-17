package com.mingzhi.samattendance.action.framework;

/**
 * Created by Chen on 2015/8/1.
 */
public class RecievCC {

        private String crackedTime;// 打卡时间
        private String crackedType;// 打卡类型
        private String areaName;// 添加区名称
        private String result;//

        public String getCrackedTime() {
            return crackedTime;
        }

        public void setCrackedTime(String crackedTime) {
            this.crackedTime = crackedTime;
        }

        public String getCrackedType() {
            return crackedType;
        }

        public void setCrackedType(String crackedType) {
            this.crackedType = crackedType;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

    }


