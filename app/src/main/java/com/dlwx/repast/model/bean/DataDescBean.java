package com.dlwx.repast.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class DataDescBean {


    /**
     * body : {"list":[{"list":[{"num":3,"xuancan":"木耳炒肉,辣子鸡丁,炒豆角"},{"num":"2","xuancan":"木耳炒肉,核桃炒石子,木须肉"}],"menu_name":"二荤呃呃呃素"},{"list":[{"num":"1","xuancan":"宫保鸡丁,木耳炒肉,酸辣土豆丝,冰镇西瓜"}],"menu_name":"O(∩_∩)O哈哈哈~"},{"list":[{"num":"1","xuancan":"宫保鸡丁,木耳炒肉,青椒土豆"}],"menu_name":"你说啥三"}],"type_id":"1"}
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
         * list : [{"list":[{"num":3,"xuancan":"木耳炒肉,辣子鸡丁,炒豆角"},{"num":"2","xuancan":"木耳炒肉,核桃炒石子,木须肉"}],"menu_name":"二荤呃呃呃素"},{"list":[{"num":"1","xuancan":"宫保鸡丁,木耳炒肉,酸辣土豆丝,冰镇西瓜"}],"menu_name":"O(∩_∩)O哈哈哈~"},{"list":[{"num":"1","xuancan":"宫保鸡丁,木耳炒肉,青椒土豆"}],"menu_name":"你说啥三"}]
         * type_id : 1
         */

        private String type_id;
        private List<ListBeanX> list;

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * list : [{"num":3,"xuancan":"木耳炒肉,辣子鸡丁,炒豆角"},{"num":"2","xuancan":"木耳炒肉,核桃炒石子,木须肉"}]
             * menu_name : 二荤呃呃呃素
             */

            private String menu_name;
            private List<ListBean> list;

            public String getMenu_name() {
                return menu_name;
            }

            public void setMenu_name(String menu_name) {
                this.menu_name = menu_name;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * num : 3
                 * xuancan : 木耳炒肉,辣子鸡丁,炒豆角
                 */

                private int num;
                private String xuancan;

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getXuancan() {
                    return xuancan;
                }

                public void setXuancan(String xuancan) {
                    this.xuancan = xuancan;
                }
            }
        }
    }
}
