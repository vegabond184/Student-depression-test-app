package com.aimtech.depressiontest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class depression_info extends AppCompatActivity {

    Button submit_btn;
    EditText age,financial,study_satisfaction,work_study,academic_pre;

    RadioGroup gender_radio,city_name,sleep_radio,su_radio,dite_radio;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_info);

        submit_btn = findViewById(R.id.submit);

        age = findViewById(R.id.age);
//        city_name = findViewById(R.id.city_name);
        financial = findViewById(R.id.financial);
        study_satisfaction = findViewById(R.id.study_satisfaction);
        work_study = findViewById(R.id.work_study);
        academic_pre = findViewById(R.id.academic_pre);

        gender_radio = findViewById(R.id.gender_radio);
        city_name = findViewById(R.id.histroy_radio);
        sleep_radio = findViewById(R.id.sleep_radio);
        su_radio = findViewById(R.id.su_radio);
        dite_radio = findViewById(R.id.dite_radio);

//        try {
//            Depression model = Depression.newInstance(context);
//
//            // Creates inputs for reference.
//            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 9}, DataType.FLOAT32);
//            inputFeature0.loadBuffer(byteBuffer);
//
//            // Runs model inference and gets result.
//            Depression.Outputs outputs = model.process(inputFeature0);
//            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//
//            // Releases model resources if no longer used.
//            model.close();
//        } catch (IOException e) {
//            // TODO Handle the exception
//        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(depression_info.this, age.getText(), Toast.LENGTH_SHORT).show();
                int gender_checked = gender_radio.getCheckedRadioButtonId();
                int hitory_checked = city_name.getCheckedRadioButtonId();
                int sleep_checked = sleep_radio.getCheckedRadioButtonId();
                int su_checked = su_radio.getCheckedRadioButtonId();
                int dite_checked = dite_radio.getCheckedRadioButtonId();

                RadioButton gender_selected = findViewById(gender_checked);
                RadioButton hitory_selected = findViewById(hitory_checked);
                RadioButton sleep_selected = findViewById(sleep_checked);
                RadioButton su_selected = findViewById(su_checked);
                RadioButton dite_selected = findViewById(dite_checked);

                if (TextUtils.isEmpty(age.getText().toString())) {
//                    Toast.makeText(depression_info.this, "empty", Toast.LENGTH_SHORT).show();
                    alert();
                } else if (TextUtils.isEmpty(academic_pre.getText().toString())) {
//                    Toast.makeText(depression_info.this, "Empty", Toast.LENGTH_SHORT).show();
                    alert();

                }
//                else if (TextUtils.isEmpty(city_name.getText().toString())) {
//                    Toast.makeText(depression_info.this, "Empty", Toast.LENGTH_SHORT).show();
//                    alert();

//                }
                else if (TextUtils.isEmpty(financial.getText().toString())) {
//                    Toast.makeText(depression_info.this, "Empty", Toast.LENGTH_SHORT).show();
                    alert();

                } else if (TextUtils.isEmpty(study_satisfaction.getText().toString())) {
//                    Toast.makeText(depression_info.this, "Empty", Toast.LENGTH_SHORT).show();
                    alert();

                } else if (TextUtils.isEmpty(work_study.getText().toString())) {
//                    Toast.makeText(depression_info.this, "Empty", Toast.LENGTH_SHORT).show();
                    alert();

//                } else if (gender_selected.getText().toString().equals("")) {
////
//                    alert();
//
//                } else if (sleep_selected.getText().toString().matches("")) {
//                    alert();
//
//                }else if (su_selected.getText().toString().matches("")) {
//                    alert();
//
//                } else if (dite_selected.getText().toString().matches("")) {
//                    alert();
//
                }
                else {
                    try {
                        String gender_text = gender_selected.getText().toString();
                        String sleep_text = sleep_selected.getText().toString();
                        String su_text = su_selected.getText().toString();
                        String dite_text = dite_selected.getText().toString();
                        String His_text = hitory_selected.getText().toString();

                        String age_text = age.getText().toString();
                        String academic_pre_text = academic_pre.getText().toString();
//                        String city_name_text = city_name.getText().toString();
                        String financial_text = financial.getText().toString();
                        String study_satisfaction_text = study_satisfaction.getText().toString();
                        String work_study_text = work_study.getText().toString();

                        Intent intent = new Intent(depression_info.this, MainActivity2.class);

                        intent.putExtra("gender", gender_text);
                        intent.putExtra("sleep", sleep_text);
                        intent.putExtra("suicidal", su_text);
                        intent.putExtra("dite", dite_text);

                        intent.putExtra("age", age_text);
                        intent.putExtra("academic", academic_pre_text);
                        intent.putExtra("city_name", His_text);
                        intent.putExtra("financial", financial_text);
                        intent.putExtra("study_satisfaction", study_satisfaction_text);
                        intent.putExtra("work_study", work_study_text);

//                        Toast.makeText(depression_info.this, His_text, Toast.LENGTH_SHORT).show();


                        // start the Intent
                        startActivity(intent);
                        overridePendingTransition(R.anim.from_right,R.anim.from_left);

//                        Toast.makeText(depression_info.this, "DONE", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                    alert();
                    }
                }
            }



            private void alert() {

                AlertDialog.Builder builder = new AlertDialog.Builder(depression_info.this);

                // Set the message show for the Alert time
                builder.setMessage("Please Answer All Questions");


                builder.setTitle("Alert!!");


                builder.setCancelable(false);

                builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {

                    // When the user click yes button then app will close
                    dialog.cancel();
                });

//                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();


            }

        });

    }
}