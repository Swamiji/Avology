package com.example.amit.avologyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

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

/**
 * Created by OAASA C on 12/10/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context context;

    public BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Information.....");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String Url="http://10.0.2.2/webapp/user_info";
        //String Url="http://10.112.162.47/webapp/user_info";
        //String Url="http://oaasa.com/live-security/app-data/register.php";
        String login_url="http://10.0.2.2/webapp/login";
        String method=params[0];
        if(method.equals("userdata")){
            String name=params[1];
            String email=params[2];
            String mobile_number=params[3];
            String gender=params[4];
            String qualification=params[5];
            String bpo_experience=params[6];
            String work_exprience=params[7];
            try {
                URL url=new URL(Url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream OS=httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(OS,"UTF-8");
                BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
                String data= URLEncoder.encode("Name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("MobileNumber","UTF-8") +"="+URLEncoder.encode(mobile_number,"UTF-8")+"&"+
                        URLEncoder.encode("Gender","UTF-8") +"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                        URLEncoder.encode("Qualification","UTF-8") +"="+URLEncoder.encode(qualification,"UTF-8")+"&"+
                        URLEncoder.encode("BPO_Experience","UTF-8") +"="+URLEncoder.encode(bpo_experience,"UTF-8")+"&"+
                        URLEncoder.encode("Work_Experience","UTF-8") +"="+URLEncoder.encode(work_exprience,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        /*else if(method.equals("login")){
            String user_login=params[1];
            String user_password=params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("Email","UTF-8")+"="+ URLEncoder.encode(user_login,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(user_password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response=" ";
                String line=" ";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                IS.close();
                bufferedReader.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return "Information is save...";

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Information is save...")){
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        }

    }
}
