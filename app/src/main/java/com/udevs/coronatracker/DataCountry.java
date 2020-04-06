package com.udevs.coronatracker;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataCountry extends AsyncTask<Void,Void,Void> {
    String data = "";
    Context c;
    ProgressDialog pd;
    String flags = "";
    String show="";

    public DataCountry(Context c) {
        this.c = c;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://corona.lmao.ninja/countries");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

                JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                JSONObject countryInfo = JO.getJSONObject("countryInfo");

                all_country.items.add(new singleRow(""+JO.get("country"),""+JO.get("cases"),
                        ""+countryInfo.get("flag"),
                        ""+JO.get("todayCases"),""+JO.get("recovered"),
                        ""+JO.get("todayDeaths"),""+JO.get("deaths")));
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c, R.style.AppCompatAlertDialogStyle);
        pd.setTitle("Loading Data");
        pd.setMessage("Please wait...");
        pd.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        all_country.list.setAdapter(all_country.adapter);
        Udevs_utils udevs = new Udevs_utils();
        pd.dismiss();
        all_country.reloadData.setRefreshing(false);

    }



}
