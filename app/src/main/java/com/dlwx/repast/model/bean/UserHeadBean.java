package com.dlwx.repast.model.bean;

/**
 * Created by Administrator on 2017/8/19/019.
 */

public class UserHeadBean {

    /**
     * body : {"header_pic":"http://192.168.0.191/hezhiqi//Uploads/5997e28a81ff9.png"}
     * code : 200
     * result : 操作成功
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
         * header_pic : http://192.168.0.191/hezhiqi//Uploads/5997e28a81ff9.png
         */

        private String header_pic;

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }
    }
}
