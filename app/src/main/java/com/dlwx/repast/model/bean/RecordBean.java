package com.dlwx.repast.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class RecordBean {

    /**
     * body : {"data":[{"list":[{"num":2,"type_id":"1","type_name":"套餐"}],"meal":"0","title":"总数量","total_num":2},{"list":[{"num":1,"type_id":"1","type_name":"套餐"}],"meal":"05","title":"凌晨五点数量","total_num":1},{"list":[{"num":1,"type_id":"1","type_name":"套餐"}],"meal":"20","title":"晚上八点数量","total_num":1}],"day_time":"2017-08-31"}
     * code : 200
     * result : 获取成功
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

    public static class BodyBean {
        /**
         * data : [{"list":[{"num":2,"type_id":"1","type_name":"套餐"}],"meal":"0","title":"总数量","total_num":2},{"list":[{"num":1,"type_id":"1","type_name":"套餐"}],"meal":"05","title":"凌晨五点数量","total_num":1},{"list":[{"num":1,"type_id":"1","type_name":"套餐"}],"meal":"20","title":"晚上八点数量","total_num":1}]
         * day_time : 2017-08-31
         */

        private String day_time;
        private List<DataBean> data;

        public String getDay_time() {
            return day_time;
        }

        public void setDay_time(String day_time) {
            this.day_time = day_time;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * list : [{"num":2,"type_id":"1","type_name":"套餐"}]
             * meal : 0
             * title : 总数量
             * total_num : 2
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
                 * num : 2
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
}
