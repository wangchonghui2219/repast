package com.dlwx.repast.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 验证成功
 */

public class VerSuccBean implements Serializable{


    /**
     * body : {"bonus_point":"0","desc":[{"menu_id":"29","menu_name":"荤素","num":"1","price":"13"}],"exten_code":"013307","meal":"晚上七点","meal_time":"2017-08-30 19:00:00","nickname":"186****1978","order_id":"86","order_no":"201708301512306WAyy9190","phone":"18637051978","pick_exten":"13307","pick_phone":"18637051978","pick_type":"1","status":"2","total_price":"13"}
     * code : 200
     * result : 领餐码验证成功
     */

    private BodyBean body;
    private int code;
    private String result;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

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

    public static class BodyBean implements Serializable{
        /**
         * bonus_point : 0
         * desc : [{"menu_id":"29","menu_name":"荤素","num":"1","price":"13"}]
         * exten_code : 013307
         * meal : 晚上七点
         * meal_time : 2017-08-30 19:00:00
         * nickname : 186****1978
         * order_id : 86
         * order_no : 201708301512306WAyy9190
         * phone : 18637051978
         * pick_exten : 13307
         * pick_phone : 18637051978
         * pick_type : 1
         * status : 2
         * total_price : 13
         */

        private String bonus_point;
        private String exten_code;
        private String meal;
        private String meal_time;
        private String nickname;
        private String order_id;
        private String order_no;
        private String phone;
        private String pick_exten;
        private String pick_phone;
        private String pick_type;
        private String status;
        private String total_price;
        private List<DescBean> desc;

        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }

        public String getExten_code() {
            return exten_code;
        }

        public void setExten_code(String exten_code) {
            this.exten_code = exten_code;
        }

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        public String getMeal_time() {
            return meal_time;
        }

        public void setMeal_time(String meal_time) {
            this.meal_time = meal_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPick_exten() {
            return pick_exten;
        }

        public void setPick_exten(String pick_exten) {
            this.pick_exten = pick_exten;
        }

        public String getPick_phone() {
            return pick_phone;
        }

        public void setPick_phone(String pick_phone) {
            this.pick_phone = pick_phone;
        }

        public String getPick_type() {
            return pick_type;
        }

        public void setPick_type(String pick_type) {
            this.pick_type = pick_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public List<DescBean> getDesc() {
            return desc;
        }

        public void setDesc(List<DescBean> desc) {
            this.desc = desc;
        }

        public static class DescBean implements Serializable{
            /**
             * menu_id : 29
             * menu_name : 荤素
             * num : 1
             * price : 13
             */

            private String menu_id;
            private String menu_name;
            private String num;
            private String price;

            public String getMenu_id() {
                return menu_id;
            }

            public void setMenu_id(String menu_id) {
                this.menu_id = menu_id;
            }

            public String getMenu_name() {
                return menu_name;
            }

            public void setMenu_name(String menu_name) {
                this.menu_name = menu_name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
