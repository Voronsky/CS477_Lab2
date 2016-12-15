package com.example.ivan.lab2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* final Button btnNoResult = (Button)findViewById(R.id.btnNoResult);
        assert btnNoResult != null;
        btnNoResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 MainActivity obj = new MainActivity();
    //            EditText userInput1 = (EditText)findViewById(R.id.inputText1);
                //input1 = Integer.parseInt(userInput1.getText().toString());
     //           EditText userInput2 = (EditText)findViewById(R.id.inputText2);
                //input2 = Integer.parseInt(userInput2.getText().toString());
                 obj.sendToCalcInput(v);

            }
        });*/
    }

    /**
     * sendToCalcInput is triggered when hitting the "start with no result" button. This opens up \
     * the other activity and then shows the result on that new activity
     * @param v - current view instance
     */
    public void sendToCalcInput(View v){
        EditText userInput1 = (EditText)findViewById(R.id.inputText1);
        EditText userInput2 = (EditText)findViewById(R.id.inputText2);
        Intent intent = new Intent(this,calcInput.class);
        String input1 = userInput1.getText().toString();
        intent.putExtra("input1", input1);
        String input2 = userInput2.getText().toString();
        intent.putExtra("input2", input2);

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }

    /**
     * Similar to the above method, it is triggered by the second button
     * @param V
     */
    public void sendWithResult(View V){
        EditText userInput1 = (EditText)findViewById(R.id.inputText1);
        EditText userInput2 = (EditText)findViewById(R.id.inputText2);
        Intent intent = new Intent(this,calcInput.class);
        String input1 = userInput1.getText().toString();
        intent.putExtra("input1", input1);
        String input2 = userInput2.getText().toString();
        intent.putExtra("input2", input2);
        startActivityForResult(intent,1);
    }

    /**
     * This is triggered when pressing the third button, which allows this app to launch Lab 1
     * @param v
     */
    public void startLabWithImplicity(View v){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setType("text/plain");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    /**
     *  onActivityResult() - Will grab data back from the calcInput, in this case it will grab the
     *  packaged result and set the result textview to show the result.
      * @param requestCode - sent from the calcInput class
      * @param resultCode - A success or failure code sent from the activity class
      * @param data - An intent object that we will use go grab data that came over if successful
     *              Most of this solution's implementation came from stackoverflow
      * @url https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Bundle bundle = data.getExtras();
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                int result = data.getIntExtra("packagedResult",0);
                TextView resultView = (TextView)findViewById(R.id.textResult);
                resultView.setText("Result = "+Integer.toString(result));
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Log.d("activityERROR", "onActivityResult: DATA WAS UNABLE TO BE RETRIEVED ");
            }
            if(data == null){
                Log.d("activityERROR", "onActivityResult: DATA IS NULL!");
            }
        }
    }

    /**
     *  This overrides the basic in-built method for when the phone changes orientation, so when \
     *  the phone changes from portrait to landscape, it will make a counter and show it in text
      * @param newConfig - a object of Configuration type which takes the instance of the phone's \
     *                   orientation
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        //Check the orientation of the screen
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            counter++;
            TextView counterText = (TextView)findViewById(R.id.textCounter);
            counterText.setText("DEBUG, Counter = "+Integer.toString(counter));
            Log.d("orientationERROR", "onConfigurationChanged: counter "+counter);
            //DEBUG: Toast.makeText(this,"landscape", Toast.LENGTH_SHORT).show();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            counter++;
            TextView counterText = (TextView)findViewById(R.id.textCounter);
            counterText.setText("DEBUG, Counter = "+Integer.toString(counter));
            Log.d("orientationERROR", "onConfigurationChanged: counter value "+counter);
            //DEBUG :Toast.makeText(this,"portrait", Toast.LENGTH_SHORT).show();
        }
    }

}
