package com.returnz3ro.imsoangry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private long backBtnTime=0;
    private EditText id;
    private EditText pw;
    private String json;
    private MakeJson Mjson;

    private String Id;
    private String Pw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        id = (EditText)findViewById(R.id.id);
        pw = (EditText)findViewById(R.id.pw);


    }


    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        long gapTime = currentTime -backBtnTime;
        if( 0 <= gapTime && 2000 >=gapTime){
            super.onBackPressed();
        }else{
            backBtnTime = currentTime;
            Toast.makeText(this, "뒤로 가기를 한번더 누르면 종료 됩니다.",Toast.LENGTH_SHORT ).show();
        }
    }



    //sign in 버튼 클릭
    public void loginBtnOnClick(View view) {

        String url = URL.url + "login";
        Mjson = new MakeJson();
        Id=id.getText().toString();
        Pw=pw.getText().toString();

        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        SharedPreferences.Editor editor = auto.edit();
        editor.putString("ID", Id);
        editor.putString("PW", Pw);
        editor.commit();


        String result=null;

        JSONParser jsonParser = new JSONParser();
        LoginData data= new LoginData();

        json = Mjson.LoginJson(Id,Pw);
        NetworkTask networkTask = new NetworkTask(url, null,json);
        try
        {
            result = networkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        data = jsonParser.LoginJSONParse(result);

        if(data.getLogin_success()==0){
            Intent intent = new Intent(this, LoginFailPopup.class);
            startActivity(intent);

        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }


}