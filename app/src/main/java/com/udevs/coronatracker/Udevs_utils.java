package com.udevs.coronatracker;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Udevs_utils {


    public static ArrayList<String> flags = new ArrayList<String>();

    public static String getFormatedAmount(int amount) {
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }


    public void show_status(Context context,String url,String s_cases,String s_deaths,String s_today_cases,String s_today_deaths,String s_recovered){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.show_status, null);
        alertDialogBuilder.setView(view);
        final AlertDialog dialog = alertDialogBuilder.create();

        ImageView country_flag = (ImageView) view.findViewById(R.id.country_flags);
        TextView cases = (TextView)view.findViewById(R.id.cases);
        TextView deaths = (TextView)view.findViewById(R.id.deaths);
        TextView today_cases = (TextView)view.findViewById(R.id.today_cases);
        TextView recovered = (TextView)view.findViewById(R.id.recovered);
        TextView today_deaths = (TextView)view.findViewById(R.id.today_deaths);

        cases.setText(getFormatedAmount(Integer.parseInt(s_cases)));
        deaths.setText(getFormatedAmount(Integer.parseInt(s_deaths)));
        today_cases.setText(getFormatedAmount(Integer.parseInt(s_today_cases)));
        today_deaths.setText(getFormatedAmount(Integer.parseInt(s_today_deaths)));
        recovered.setText(getFormatedAmount(Integer.parseInt(s_recovered)));
        Button close = (Button)view.findViewById(R.id.close);
        Picasso.get().load(url).into(country_flag);

        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
