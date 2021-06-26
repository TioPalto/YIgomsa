package com.example.testapplication.bena;

import java.util.List;

public class RechargeListBean extends BaseBean {

    private List<RechargeBean> data;

    public RechargeListBean(int code, String message, List<RechargeBean> data) {
        super(code, message);
        this.data = data;
    }

    public List<RechargeBean> getData() {
        return data;
    }

    public void setData(List<RechargeBean> data) {
        this.data = data;
    }

    public class RechargeBean {

        private String rechargeTime;
        private String userId;
        private String money;

        public RechargeBean(String rechargeTime, String userId, String money) {
            this.rechargeTime = rechargeTime;
            this.userId = userId;
            this.money = money;
        }

        public String getRechargeTime() {
            return rechargeTime;
        }

        public void setRechargeTime(String rechargeTime) {
            this.rechargeTime = rechargeTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

}
