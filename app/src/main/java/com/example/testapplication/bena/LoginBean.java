package com.example.testapplication.bena;

public class LoginBean extends BaseBean {

    private UserBean data;

    public LoginBean(int code, String message, UserBean data) {
        super(code, message);
        this.data = data;
    }

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

    public class UserBean {
       private String userId;
       private String name;
       private String headImage;
       private Number balance;
       private String phoneNum;
       private String password;

       public UserBean(String userId, String name,String headImage,
                        Number balance, String phoneNum, String password) {
           this.userId = userId;
           this.name = name;
           this.headImage = headImage;
           this.balance = balance;
           this.phoneNum = phoneNum;
           this.password = password;
       }

       public String getUserId() {
           return userId;
       }

       public void setUserId(String userId) {
           this.userId = userId;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getHeadImage() {
           return headImage;
       }

       public void setHeadImage(String headImage) {
           this.headImage = headImage;
       }

       public Number getBalance() {
           return balance;
       }

       public void setBalance(Number balance) {
           this.balance = balance;
       }

       public String getPhoneNum() {
           return phoneNum;
       }

       public void setPhoneNum(String phoneNum) {
           this.phoneNum = phoneNum;
       }

       public String getPassword() {
           return password;
       }

       public void setPassword(String password) {
           this.password = password;
       }
   }
}
