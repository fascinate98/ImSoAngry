package com.returnz3ro.imsoangry;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private LoginData Logindata;

    public LoginData LoginJSONParse(String jsonStr)
    {
        Logindata = new LoginData();

        try
        {
            JSONObject jsonObject = new JSONObject(jsonStr);

            Logindata.setLogin_success(jsonObject.getInt("login_success"));
            Logindata.setUser_id(jsonObject.getInt("user_id"));
            Logindata.setUser_pw(jsonObject.getString("user_pw"));
            Logindata.setUser_name(jsonObject.getString("user_name"));
            Logindata.setUser_work_type(jsonObject.getString("user_work_type"));
            Logindata.setUser_group(jsonObject.getString("user_group"));

        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return Logindata;

    }


}