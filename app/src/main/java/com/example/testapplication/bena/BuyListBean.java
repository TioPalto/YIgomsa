package com.example.testapplication.bena;

import java.util.List;

public class BuyListBean extends BaseBean {

    private List<BuyRecord> data;

    public BuyListBean(int code, String message, List<BuyRecord> data) {
        super(code, message);
        this.data = data;
    }

    public List<BuyRecord> getData() {
        return data;
    }

    public void setData(List<BuyRecord> data) {
        this.data = data;
    }

    public class BuyRecord {
        private String orderNum;
        private String userId;
        private String createTime;
        private Number money;
        private String addrId;
        private String status;
        private String message;

        public BuyRecord(String orderNum, String userId, String createTime, Number money,
                         String addrId, String status, String message) {
            this.orderNum = orderNum;
            this.userId = userId;
            this.createTime = createTime;
            this.money = money;
            this.addrId = addrId;
            this.status = status;
            this.message = message;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Number getMoney() {
            return money;
        }

        public void setMoney(Number money) {
            this.money = money;
        }

        public String getAddrId() {
            return addrId;
        }

        public void setAddrId(String addrId) {
            this.addrId = addrId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
