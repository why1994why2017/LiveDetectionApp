package com.example.wang.livedetectionapp.tool;

import com.example.wang.livedetectionapp.mode.MenuInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NetManager {

    public static String linkHttp(String strURL) {
        URL url;
        HttpURLConnection httpURLConnection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            url = new URL(strURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(6000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static List<MenuInfo> parseIndexGson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String data = jsonObject.getString("data");

            Gson gson = new Gson();
            List<MenuInfo> menuInfos = gson.fromJson(data, new TypeToken<List<MenuInfo>>(){}.getType());

            return menuInfos;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static DetailInfo parseDetailGson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String data = jsonObject.getString("data");

            Gson gson = new Gson();
            DetailInfo detailInfo = gson.fromJson(data, DetailInfo.class);

            return detailInfo;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
