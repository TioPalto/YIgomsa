package com.example.testapplication.bena;

public class SubsInfoBean extends BaseBean{
    private SubsData data;

    public SubsData getData() {
        return data;
    }

    public void setData(SubsData data) {
        this.data = data;
    }

    public SubsInfoBean(int code, String message, SubsData data) {
        super(code, message);
        this.data = data;
    }

    public class SubsData {
        private String bookId;
        private String userId;
        private String bookTime;
        private String comeTime;
        private String bookItem;
        private String message;
        private String status;

        public SubsData(String bookId, String userId, String bookTime, String comeTime, String bookItem, String message, String status) {
            this.bookId = bookId;
            this.userId = userId;
            this.bookTime = bookTime;
            this.comeTime = comeTime;
            this.bookItem = bookItem;
            this.message = message;
            this.status = status;
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBookTime() {
            return bookTime;
        }

        public void setBookTime(String bookTime) {
            this.bookTime = bookTime;
        }

        public String getComeTime() {
            return comeTime;
        }

        public void setComeTime(String comeTime) {
            this.comeTime = comeTime;
        }

        public String getBookItem() {
            return bookItem;
        }

        public void setBookItem(String bookItem) {
            this.bookItem = bookItem;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
