package com.example.aerotrackerapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Background extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context context;

    Background(Context ctx) {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String sync_url = Connection.SERVER_URL;
        String physical_url = Connection.PHYSICAL_URL;
        String athlete_url = Connection.ATHLETE_URL;
        String coach_url = Connection.COACH_URL;

        //String SERVER_URL = "http://192.168.0.109/aerotracker/sync.php";
        // String physical_url = "http://192.168.0.1/aerotracker/physicalpreparation.php";
        // String athlete_url = "http://192.168.0.1/aerotracker/athlete.php";
        //String coach_url = "http://192.168.0.1/aerotracker/coach.php";
     //   String results_url = "http://192.168.0.1/aerotracker/results.php";
        if (type.equals("add")) {
            try {
                String id = params[1];
                String name = params[2];
                String surname = params[3];
                String age = params[4];
                String efficiency = params[5];
                String intensity = params[6];
                String ws = params[7];
                String we = params[8];
                String ms = params[9];
                String me = params[10];
                String physical_preparation = params[11];
                String category = params[12];
                String period = params[13];
                URL url = new URL(sync_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                        + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                        + URLEncoder.encode("efficiency", "UTF-8") + "=" + URLEncoder.encode(efficiency, "UTF-8") + "&"
                        + URLEncoder.encode("intensity", "UTF-8") + "=" + URLEncoder.encode(intensity, "UTF-8") + "&"
                        + URLEncoder.encode("ws", "UTF-8") + "=" + URLEncoder.encode(ws, "UTF-8") + "&"
                        + URLEncoder.encode("we", "UTF-8") + "=" + URLEncoder.encode(we, "UTF-8") + "&"
                        + URLEncoder.encode("ms", "UTF-8") + "=" + URLEncoder.encode(ms, "UTF-8") + "&"
                        + URLEncoder.encode("me", "UTF-8") + "=" + URLEncoder.encode(me, "UTF-8") + "&"
                        + URLEncoder.encode("physical_preparation", "UTF-8") + "=" + URLEncoder.encode(physical_preparation, "UTF-8") + "&"
                        + URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(category, "UTF-8") + "&"
                        + URLEncoder.encode("period", "UTF-8") + "=" + URLEncoder.encode(period, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("preparation")) {
            try {
                String preparatory = params[1];
                String precompetitive = params[2];
                String competitive = params[3];
                String postcompetitive = params[4];
                URL url = new URL(physical_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("preparatory", "UTF-8") + "=" + URLEncoder.encode(preparatory, "UTF-8") + "&"
                        + URLEncoder.encode("precompetitive", "UTF-8") + "=" + URLEncoder.encode(precompetitive, "UTF-8") + "&"
                        + URLEncoder.encode("competitive", "UTF-8") + "=" + URLEncoder.encode(competitive, "UTF-8") + "&"
                        + URLEncoder.encode("postcompetitive", "UTF-8") + "=" + URLEncoder.encode(postcompetitive, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("athlete")) {
            try {
                String name = params[1];
                String surname = params[2];
                String birthDate = params[3];
                String pass = params[4];
                URL url = new URL(athlete_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                        + URLEncoder.encode("birthDate", "UTF-8") + "=" + URLEncoder.encode(birthDate, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("coach")) {
            try {
                String name = params[1];
                String surname = params[2];
                String trainingType = params[3];
                String pass = params[4];
                URL url = new URL(coach_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                        + URLEncoder.encode("trainingType", "UTF-8") + "=" + URLEncoder.encode(trainingType, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("results")) {
            try {
                String efficiency = params[1];
                String intensity = params[2];
                String idAth = params[3];
                String idCat = params[4];
                String idPeriod = params[5];
                String idHours = params[6];
                String idPhysical = params[7];
                URL url = new URL(coach_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("efficiency", "UTF-8") + "=" + URLEncoder.encode(efficiency, "UTF-8") + "&"
                        + URLEncoder.encode("intensity", "UTF-8") + "=" + URLEncoder.encode(intensity, "UTF-8") + "&"
                        + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(idAth, "UTF-8") + "&"
                        + URLEncoder.encode("categoryId", "UTF-8") + "=" + URLEncoder.encode(idCat, "UTF-8") + "&"
                        + URLEncoder.encode("periodId", "UTF-8") + "=" + URLEncoder.encode(idPeriod, "UTF-8") + "&"
                        + URLEncoder.encode("hoursId", "UTF-8") + "=" + URLEncoder.encode(idHours, "UTF-8") + "&"
                        + URLEncoder.encode("preparationId", "UTF-8") + "=" + URLEncoder.encode(idPhysical, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Data status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
