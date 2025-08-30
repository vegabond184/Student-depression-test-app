package com.aimtech.depressiontest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button depression;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        depression = findViewById(R.id.depression_btn);

        String url = "https://depressiontest.onrender.com/depression";

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        if (connected) {
            // Internet is available
            send_req_to_the_server(url);
        } else {
            // No internet connection
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Set the message show for the Alert time
            builder.setMessage("Please check your internet connection...");
            // Set Alert Title
            builder.setTitle("Alert");

            builder.setCancelable(false);

            builder.setPositiveButton("Exit", (DialogInterface.OnClickListener) (dialog, which) -> {
                finishAffinity();

                // destroys the current running process using System.exit()
                System.exit(0);
            });

            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();

        }


        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("This AI-based depression test especially made for students. by answering 10 questions it can tell the possibility of having depression");

                // Set Alert Title
                builder.setTitle("Student Depression Test !");

                // Set Cancelable false for when the user clicks
                // on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda
                // OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Start Test", (DialogInterface.OnClickListener) (dialog, which) -> {

                    // When the user click yes button then app will close
                    startActivity(new Intent(MainActivity.this,depression_info.class));
                    overridePendingTransition(R.anim.from_right,R.anim.from_left);
                });

                // Set the Negative button with No name Lambda
                // OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("Back", (DialogInterface.OnClickListener) (dialog, which) -> {

                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();


            }
        });




    }

    private void send_req_to_the_server(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("prediction");

//                            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Server Is Down Try Few Minutes Latter..", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
//                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("age", "19");
                params.put("academic_pressure", "2");
                params.put("study_satisfaction", "2");
                params.put("work_study", "5");
                params.put("financial", "2");
                params.put("genderencode", "Male");
                params.put("suicidalencoded", "No");
                params.put("sleep", "Less than 5 hours");
                params.put("dietary", "Healthy");
                params.put("cityencode", "No");

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);


    }

}