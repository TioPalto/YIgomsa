package com.example.testapplication.bena;

public class GoodInfoBean extends BaseBean {

    private InfoBean data;

    public GoodInfoBean(int code, String message, InfoBean data) {
        super(code, message);
        this.data = data;
    }

    public InfoBean getData() {
        return data;
    }

    public void setData(InfoBean data) {
        this.data = data;
    }

    public class InfoBean {
        private String goodsId;
        private String name;
        private Number price;
        private String image;
        private String detail;
        private String upTime;

        public InfoBean(String goodsId, String name, int price, String image, String detail, String upTime) {
            this.goodsId = goodsId;
            this.name = name;
            this.price = price;
            this.image = image;
            this.detail = detail;
            this.upTime = upTime;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Number getPrice() {
            return price;
        }

        public void setPrice(Number price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUpTime() {
            return upTime;
        }

        public void setUpTime(String upTime) {
            this.upTime = upTime;
        }
    }

}
