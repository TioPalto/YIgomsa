package com.example.testapplication.bena;



import java.util.List;

public class OrderInfoBean extends BaseBean{

    private InfoData data;
    public OrderInfoBean(int code, String message) {
        super(code, message);
    }

    public InfoData getData() {
        return data;
    }

    public void setData(InfoData data) {
        this.data = data;
    }

    public class InfoData {
        private Order order;
        private Address address;
        private List<Good> item_list;

        public InfoData(Order order, Address address, List<Good> item_list) {
            this.order = order;
            this.address = address;
            this.item_list = item_list;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<Good> getItem_list() {
            return item_list;
        }

        public void setItem_list(List<Good> item_list) {
            this.item_list = item_list;
        }
    }

    public class Order {
        private String orderNum;
        private String userId;
        private String createTime;
        private Number money;
        private String addrId;
        private String status;
        private String message;

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

        public Order(String orderNum, String userId, String createTime, Number money, String addrId, String status, String message) {
            this.orderNum = orderNum;
            this.userId = userId;
            this.createTime = createTime;
            this.money = money;
            this.addrId = addrId;
            this.status = status;
            this.message = message;
        }
    }

    public class Good {
        private String image;
        private String price;
        private String num;
        private String name;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Good(String image, String price, String num, String name) {
            this.image = image;
            this.price = price;
            this.num = num;
            this.name = name;
        }
    }
    public class Address {
        private String addrId;
        private String name;
        private String detail;
        private String province;
        private String city;
        private String district;
        private String phone;
        private boolean defaultFlag;
        private String userId;
        private boolean deleteFlag;

        public String getAddrId() {
            return addrId;
        }

        public void setAddrId(String addrId) {
            this.addrId = addrId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isDefaultFlag() {
            return defaultFlag;
        }

        public void setDefaultFlag(boolean defaultFlag) {
            this.defaultFlag = defaultFlag;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Address(String addrId, String name, String detail, String province, String city, String district, String phone, boolean defaultFlag, String userId, boolean deleteFlag) {
            this.addrId = addrId;
            this.name = name;
            this.detail = detail;
            this.province = province;
            this.city = city;
            this.district = district;
            this.phone = phone;
            this.defaultFlag = defaultFlag;
            this.userId = userId;
            this.deleteFlag = deleteFlag;
        }
    }
}
