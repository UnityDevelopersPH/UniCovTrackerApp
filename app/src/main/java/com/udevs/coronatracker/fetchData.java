package com.udevs.coronatracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    Context context;
    String cases,deaths,recovered;
    ProgressDialog pd;

    public fetchData(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://corona.lmao.ninja/all");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line= "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsonObject = new JSONObject(data);
            cases = jsonObject.getString("cases");
            deaths = jsonObject.getString("deaths");
            recovered = jsonObject.getString("recovered");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        pd.setTitle("Loading Data");
        pd.setMessage("Please wait...");
        pd.show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.total_cases.setText(Udevs_utils.getFormatedAmount(Integer.parseInt(this.cases)));
        MainActivity.total_deaths.setText(Udevs_utils.getFormatedAmount(Integer.parseInt(this.deaths)));
        MainActivity.total_recovered.setText(Udevs_utils.getFormatedAmount(Integer.parseInt(this.recovered)));
        pd.dismiss();
        MainActivity.refreshData.setRefreshing(false);

    }

}
