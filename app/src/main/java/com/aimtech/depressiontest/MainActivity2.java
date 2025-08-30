package com.aimtech.depressiontest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import org.tensorflow.DataType;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;




public class MainActivity2 extends AppCompatActivity {
//    TextView result = findViewById(R.id.textView7);
//    TextView note = findViewById(R.id.note);


    String age;
    String gender;
    String academic;
    String city;
    String study;
    String work_study;
    String financial;
    String sleep;
    String dite;
    String suicidal;


    private ProgressBar loadingPB;
    private boolean isProgressVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView result = findViewById(R.id.result);
        TextView note = findViewById(R.id.note);
        Button btn = findViewById(R.id.button2);

        loadingPB = findViewById(R.id.progressBar);
        
        
        String url = "https://depressiontest.onrender.com/depression";
//
//        if (isProgressVisible) {
//            // Hide progress bar and update button text
//            Toast.makeText(this, "prog", Toast.LENGTH_SHORT).show();
//                loadingPB.setVisibility(View.GONE);
//                isProgressVisible = false;
//        } else {
//            // Show progress bar and update button text
//            Toast.makeText(this, "no pro", Toast.LENGTH_SHORT).show();
//            loadingPB.setVisibility(View.VISIBLE);
//            isProgressVisible = true;
//        }
        
        

        Intent intent = getIntent();
//
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");
        academic = intent.getStringExtra("academic");
        city = intent.getStringExtra("city_name");
        study = intent.getStringExtra("study_satisfaction");
         work_study = intent.getStringExtra("work_study");
        financial = intent.getStringExtra("financial");
        sleep = intent.getStringExtra("sleep");
        dite = intent.getStringExtra("dite");
        suicidal = intent.getStringExtra("suicidal");
//
//        Toast.makeText(this, age, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("prediction");
                            isProgressVisible = false;

//                            Toast.makeText(MainActivity2.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//
                            if(data.equals("1")){
                                loadingPB.setVisibility(View.GONE);

//                                Toast.makeText(MainActivity2.this, "1", Toast.LENGTH_SHORT).show();
                                result.setText("You have a possibility of having depression");
                                result.setTextColor(Color.parseColor("#FF0000"));
                                note.setText("This result doesn't mean you have depression but there is a possibility, we advice you to seek expert's help");
                            }else{
                                loadingPB.setVisibility(View.GONE);
//                                Toast.makeText(MainActivity2.this, "0", Toast.LENGTH_SHORT).show();
                                result.setText("You don't have a possibility of having depression");
                                result.setTextColor(Color.parseColor("#4CFF00"));
                                note.setText("There is a less possibility of you having depression, but still take care");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        alert();
                        Toast.makeText(MainActivity2.this, error.toString(), Toast.LENGTH_SHORT).show();
//                        while (isProgressVisible){
//
//                            send_req_to_the_server(url);
//
//
//
//                        }



                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("age", age);
                params.put("academic_pressure", academic);
                params.put("study_satisfaction", study);
                params.put("work_study", work_study);
                params.put("financial", financial);
                params.put("genderencode", gender);
                params.put("suicidalencoded", suicidal);
                params.put("sleep", sleep);
                params.put("dietary", dite);
                params.put("cityencode", city);

                return params;
            }


        };

//        while (isProgressVisible){
//            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
            queue.add(stringRequest);


//        }







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                overridePendingTransition(R.anim.to_bottam,R.anim.from_top);
            }
        });






    }


    private void send_req_to_the_server(String url){

        Toast.makeText(this, "sending req", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("prediction");

                            Toast.makeText(MainActivity2.this, data, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity2.this, "no no req", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity2.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("age", age);
                params.put("academic_pressure", academic);
                params.put("study_satisfaction", study);
                params.put("work_study", work_study);
                params.put("financial", financial);
                params.put("genderencode", gender);
                params.put("suicidalencoded", suicidal);
                params.put("sleep", sleep);
                params.put("dietary", dite);
                params.put("cityencode", city);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        queue.add(stringRequest);


    }


    private void alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);

        // Set the message show for the Alert time
        builder.setMessage("Error in server");


        builder.setTitle("Alert!!");


        builder.setCancelable(true);

        builder.setPositiveButton("try again", (DialogInterface.OnClickListener) (dialog, which) -> {

            // When the user click yes button then app will close
            startActivity(new Intent(MainActivity2.this,depression_info.class));
        });

//                });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();


    }

}