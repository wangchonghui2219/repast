package com.dlwx.repast.model.bean;

/**
 * Created by Administrator on 2017/9/6/006.
 */

public class VersionBean {

    /**
     * code : 200
     * result : 需要升级最新版本
     * body : {"new_num":"2","downurl":"http://47.94.107.189/hezhiqi//apk/andriod_1.0.apk"}
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
         * new_num : 2
         * downurl : http://47.94.107.189/hezhiqi//apk/andriod_1.0.apk
         */

        private int new_num;
        private String downurl;

        public int getNew_num() {
            return new_num;
        }

        public void setNew_num(int new_num) {
            this.new_num = new_num;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }
    }
}
