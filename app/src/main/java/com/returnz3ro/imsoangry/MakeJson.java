package com.returnz3ro.imsoangry;
import org.json.JSONObject;

public class MakeJson {

    public String LoginJson(String Id,String Pw) {

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.accumulate("user_id", Id);
            jsonObject.accumulate("user_pw", Pw);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return jsonObject.toString();
    }
}
