package com.example.aerotrackerapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

class BackgroundLoadTask extends AsyncTask<Void, Void, Void> {
    Context ctx;
    ProgressDialog progressDialog;


    public BackgroundLoadTask(Context ctx) {
        this.ctx = ctx;

    }

   // String json_string = "http://192.168.0.1/aerotracker/show.php";
    String json_string=Connection.JSON_STRING;

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Download in progress...");
        progressDialog.show();
        progressDialog.setCancelable(false); //трябва да го махна ако не ползвам ип за демонстрацията
        progressDialog.show();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                Thread.sleep(500);
            }

            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_data);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            DataBaseHelper dataBaseHelper = new DataBaseHelper(ctx);
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                dataBaseHelper.putInfoFromServer(JO.getString("idAthlete"), JO.getString("name"), JO.getString("surname"),
                        JO.getString("birthDate"), JO.getString("ws"), JO.getString("we"), JO.getString("ms"),
                        JO.getString("me"), JO.getString("efficiency"), JO.getString("intensity"), JO.getString("physicalPrep"),
                        JO.getString("category"), JO.getString("period"), db);
            }
            dataBaseHelper.close();
            //  Log.d("JSON STRING", json_string);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

    @Override
    protected void onPostExecute(Void result) {

        progressDialog.dismiss();
    }


}
