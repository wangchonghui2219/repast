package com.dlwx.repast.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class RecordDescBean {

    /**
     * body : {"data":[{"list":[{"menu_id":"29","menu_name":"二荤呃呃呃素","num":"1"},{"menu_id":"27","menu_name":"二荤一素一其他","num":2}],"total_num":3,"type_id":"1","type_name":"套餐"}],"title":"总数量"}
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
         * data : [{"list":[{"menu_id":"29","menu_name":"二荤呃呃呃素","num":"1"},{"menu_id":"27","menu_name":"二荤一素一其他","num":2}],"total_num":3,"type_id":"1","type_name":"套餐"}]
         * title : 总数量
         */

        private String title;
        private List<DataBean> data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * list : [{"menu_id":"29","menu_name":"二荤呃呃呃素","num":"1"},{"menu_id":"27","menu_name":"二荤一素一其他","num":2}]
             * total_num : 3
             * type_id : 1
             * type_name : 套餐
             */

            private int total_num;
            private String type_id;
            private String type_name;
            private List<ListBean> list;

            public int getTotal_num() {
                return total_num;
            }

            public void setTotal_num(int total_num) {
                this.total_num = total_num;
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * menu_id : 29
                 * menu_name : 二荤呃呃呃素
                 * num : 1
                 */

                private String menu_id;
                private String menu_name;
                private String num;

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
            }
        }
    }
}
