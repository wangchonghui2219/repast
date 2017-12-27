package com.dlwx.repast.model.bean;

/**
 * Created by Administrator on 2017/8/16/016.
 */

public class LoginBean {


    /**
     * code : 200
     * result : 登录成功
     * body : {"token":"960b6a85f316312e696c5170a8d24cc0","phone":"18637051978","nickname":"186****1978","grade":"1","header_pic":"http://192.168.0.191/hezhiqi/Uploads/offer.jpg","seller_id":"4"}
     */

    private int code;
    private String result;
    private BodyBean body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * token : 960b6a85f316312e696c5170a8d24cc0
         * phone : 18637051978
         * nickname : 186****1978
         * grade : 1
         * header_pic : http://192.168.0.191/hezhiqi/Uploads/offer.jpg
         * seller_id : 4
         */

        private String token;
        private String phone;
        private String nickname;
        private String grade;
        private String header_pic;
        private String seller_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }
    }
}
