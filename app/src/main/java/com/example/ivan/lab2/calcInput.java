package com.example.ivan.lab2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class calcInput extends AppCompatActivity {

    int packagedResult = 0;
    String value1;
    String value2;

    /**
     * Since this is our second activity in which in this assignment, the MainActivity will call \
     * in the onCreate method, it will grab the input and manipulate their values here
     * Each intent call will trigger this method and give it's own instance
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_input);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        value1 = bundle.getString("input1");
        value2 = bundle.getString("input2");

        if (value1.isEmpty() && !value2.isEmpty()){
            value1 = "0";
        }else if(!value1.isEmpty() && value2.isEmpty()){
           value2 = "0";
        }else if(value1.isEmpty() && value2.isEmpty()){
           value1=value2="0";
        }

        try {
            TextView calcOutput = (TextView)findViewById(R.id.calculate);
            assert calcOutput != null;
            int num1 = Integer.parseInt(value1);
            int num2 = Integer.parseInt(value2);
            packagedResult = num1*num2;
            calcOutput.setText(value1 + "*" + value2+ "=" + packagedResult);


        }catch(Exception e){
            Log.d("exceptERROR", "onCreate:ERROR occurred "+e);
        }
    }


    /**
     *  This method overrides the in-built method for back pressing, when the back button is pressed
     *  this method will return to the MainActivity or the intent caller, with the result packaged
     *  in
     */
    @Override
    public void onBackPressed(){
        Intent returnIntent = getIntent();
        System.out.println("packaged result: "+packagedResult);
        returnIntent.putExtra("packagedResult",packagedResult);
        setResult(Activity.RESULT_OK, returnIntent);
        finish(); //Close activity
    }



}
