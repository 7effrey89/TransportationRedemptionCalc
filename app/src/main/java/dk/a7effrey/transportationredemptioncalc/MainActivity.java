package dk.a7effrey.transportationredemptioncalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static dk.a7effrey.transportationredemptioncalc.R.id.home2Client;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void timeCalc(View view) {
        TextView paidTime = (TextView) findViewById(R.id.PaidTransTime);
        EditText home2Office = (EditText) findViewById(R.id.home2Office);
        EditText home2Client = (EditText) findViewById(R.id.home2Client);
        int home2OfficeValue = 0;
        int home2ClientValue = 0;
        int total = 0;

        //we cannot parse a null string to an int, it will cause an error.
        //making control state flows to check the string value as "" didn't work. still app crash
        home2OfficeValue = Integer.parseInt(home2Office.getText().toString());
        home2ClientValue = Integer.parseInt(home2Client.getText().toString());

        //Control flow for calculation of paid transportation time
        if (home2ClientValue < home2OfficeValue) {
            // If time to client is shorter than office, then no pay back
            paidTime.setText("0");
        } else if (home2OfficeValue < 30) {
            // 30 min of the transportation time is paid by the consultant if he/she has less than 30 min to office
            total = home2ClientValue - 30;
            if (total < 0) {
                //if negative value then there is no paid transportation time
                paidTime.setText("0");
            } else {    //calculate the paid transportation time where 30 min has been substracted
                paidTime.setText(Integer.toString(total));
            }

        } else {
            //calculate the paid transportation time
            total = home2ClientValue - home2OfficeValue;
            paidTime.setText(Integer.toString(total));
        }

    }
}
