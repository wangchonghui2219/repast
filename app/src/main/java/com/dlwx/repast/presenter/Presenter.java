package com.dlwx.repast.presenter;

import com.dlwx.repast.model.ModeImpl;
import com.dlwx.repast.model.ModeInterface;
import com.dlwx.repast.view.ViewInterface;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/12/012.
 */

public class Presenter <V>extends BasePresenter{

        ViewInterface viewInterface;
    ModeImpl mode = new ModeImpl();

    public Presenter(ViewInterface viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }
    //绑定view和mode
    public void fetch(Map<String,String> map,Boolean isget,String url,String cachKey){
        viewInterface.showLoading();

        if (mode != null) {
            mode.loadData(new ModeInterface.LoadListener() {
                @Override
                public void complete(String s) {
                    viewInterface.showData(s);
                }

                @Override
                public void onError() {
                    viewInterface.onError();
                }
            },map,isget,url,cachKey);
        }

    }
}
