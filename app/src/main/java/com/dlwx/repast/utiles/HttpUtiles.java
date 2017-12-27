package com.dlwx.repast.utiles;

/**
 * Created by Administrator on 2017/8/16/016.
 */

public class HttpUtiles {

//    public static String BaseUrl = "Http://192.168.0.191/hezhiqi/index.php/Seller/";
    public static String BaseUrl = "Http://47.94.107.189/hezhiqi/index.php/Seller/";
    public static String Register = BaseUrl+"Register/seller_add";//注册
    public static String Login = BaseUrl +"Login/login";//登录
    public static String ForgetPwd = BaseUrl +"Register/find_pwd";//忘记密码
    public static String ChangePwd = BaseUrl + "Seller/update_pwd";//修改登录密码
    public static String ChangeNickName = BaseUrl +"Seller/update_info";//修改昵称
    public static String ChangePhone = BaseUrl+"Seller/update_info";//修改手机号
    public static String UpHeadPic = BaseUrl+"Seller/update_info";//修改头像
    public static String Record = BaseUrl + "Order/day_record";//扫码纪录
    public static String Scan_Code = BaseUrl + "Order/scan_code";//扫面领券码
    public static String Aff_NeckMEal = BaseUrl + "Order/confirm_meal";//确认领餐
    public static String History_Record = BaseUrl + "Order/history_record";//历史记录
    public static String Recore_desc = BaseUrl + "Order/one_record";//纪录详情
    public static String Meal_Data = BaseUrl + "Order/meal_data";//订餐数据
    public static String Data_Desc_title = BaseUrl + "Mobile/classify";//订餐数据分类
    public static String Order_Meal = BaseUrl + "Order/one_meal";//
    public static String upVersion = BaseUrl + "Version/getversion";//版本更新
    public static String About_We = "http://47.94.107.189/hezhiqi/Web/aboutUs_seller.html";//关于我们
    public static String SMs = BaseUrl + "Register/hezhi";//短信验证码
}