package com.example.vnguyen.tipscalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateTips(View view) {
        TextView billResultTV = (TextView) findViewById(R.id.lbl_bill_result);
        TextView tipResultTV = (TextView) findViewById(R.id.lbl_tip_result);
        TextView totalResultTV = (TextView) findViewById(R.id.lbl_total_result);

        try {
            double inTotal = Double.parseDouble(((EditText) findViewById(R.id.in_total)).getText().toString());
            double inNumPerson = Double.parseDouble(((EditText) findViewById(R.id.in_num_person)).getText().toString());
            double inTipPercent = Double.parseDouble(((EditText) findViewById(R.id.in_tip_percent)).getText().toString());

            double tipResult, totalResult, billResult;
            if (inNumPerson <= 0 || inTotal <= 0 || inTipPercent < 0) {
                tipResultTV.setText("Invalid values at Total, Number of person, or Tip percentage");
                totalResultTV.setText("Please try again");
            } else {
                billResult = inTotal / inNumPerson;
                tipResult = billResult * (inTipPercent / 100);
                totalResult = billResult + tipResult;
                // display the output
                TextView resultText = (TextView) findViewById(R.id.lbl_result);
                resultText.setText(getString(R.string.result));

                String billResultText = String.format("\t\t$%.2f for Food", billResult);
                billResultTV.setText(billResultText);

                String tipResultText = String.format("\t\t$%.2f for Tip", tipResult);
                tipResultTV.setText(tipResultText);

                String totalResultText = String.format("\t\t$%.2f total", totalResult);
                totalResultTV.setText(totalResultText);
            }
        } catch (NullPointerException e) {
            tipResultTV.setText("Invalid values at Total, Number of person, or Tip percentage");
            totalResultTV.setText("Please try again");
        } catch (NumberFormatException n) {
            tipResultTV.setText("Invalid values at Total, Number of person, or Tip percentage");
            totalResultTV.setText("Please try again");
        }




    }
}
