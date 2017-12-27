package com.dlwx.repast.utiles;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.dlwx.repast.R;
import com.dlwx.repast.interfac.publicInterface;


/**
 * Created by Administrator on 2017/6/30/030.
 */

public class DataSeleteUtiles {

    private static ViewHolder vh;
    private static AlertDialog.Builder dialog;
    private static AlertDialog show;
    private publicInterface.DateInterface dateInterface;
    public void setDate(publicInterface.DateInterface dateInterface){
        this.dateInterface = dateInterface;
    }
    public  void showDialog(Context ctx) {
        dialog = new AlertDialog.Builder(ctx);
        View view = LayoutInflater.from(ctx).inflate(R.layout.dialog_data_selete, null);
        vh = new ViewHolder(view);
        dialog.setView(view);
        vh.btn_aff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateInterface.getDate(getDate());
                show.dismiss();
            }
        });
        vh.btn_clos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });

        show = dialog.show();

    }

    public  String getDate(){
        int year = vh.datePicker.getYear();
        int month = vh.datePicker.getMonth()+1;
        int dayOfMonth = vh.datePicker.getDayOfMonth();
        return year+"-"+month+"-"+dayOfMonth;
    }




    private static class ViewHolder {
        public View rootView;
        public DatePicker datePicker;
        public Button btn_clos;
        public Button btn_aff;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
            this.btn_clos = (Button) rootView.findViewById(R.id.btn_clos);
            this.btn_aff = (Button) rootView.findViewById(R.id.btn_aff);
        }

    }
}
