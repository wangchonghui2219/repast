package com.dlwx.repast.model.bean;

import java.util.List;

/**
 * 订单详情分类头
 */

public class DataDescClassBean {

    /**
     * body : {"classify":[{"type_id":"1","type_name":"套餐"},{"type_id":"2","type_name":"面食"},{"type_id":"3","type_name":"汤/粥"},{"type_id":"4","type_name":"饮品"},{"type_id":"5","type_name":"水果"},{"type_id":"6","type_name":"小吃"},{"type_id":"7","type_name":"甜点"},{"type_id":"8","type_name":"其他"}]}
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
        private List<ClassifyBean> classify;

        public List<ClassifyBean> getClassify() {
            return classify;
        }

        public void setClassify(List<ClassifyBean> classify) {
            this.classify = classify;
        }

        public static class ClassifyBean {
            /**
             * type_id : 1
             * type_name : 套餐
             */

            private String type_id;
            private String type_name;

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
