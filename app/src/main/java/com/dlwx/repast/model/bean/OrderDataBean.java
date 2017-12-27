package com.dlwx.repast.model.bean;

import java.util.List;

/**
 *订单数据
 */

public class OrderDataBean {

    /**
     * body : [{"list":[{"num":4,"type_id":"1","type_name":"套餐"}],"meal":"0","title":"总数量","total_num":4},{"list":[{"num":2,"type_id":"1","type_name":"套餐"}],"meal":"12","title":"中午十二点数量","total_num":2},{"list":[{"num":"1","type_id":"1","type_name":"套餐"}],"meal":"18","title":"下午六点数量","total_num":1},{"list":[{"num":"1","type_id":"1","type_name":"套餐"}],"meal":"19","title":"晚上七点数量","total_num":1}]
     * code : 200
     * result : 获取成功
     */

    private int code;
    private String result;
    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * list : [{"num":4,"type_id":"1","type_name":"套餐"}]
         * meal : 0
         * title : 总数量
         * total_num : 4
         */

        private String meal;
        private String title;
        private int total_num;
        private List<ListBean> list;

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * num : 4
             * type_id : 1
             * type_name : 套餐
             */

            private int num;
            private String type_id;
            private String type_name;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }
        }
    }
}
