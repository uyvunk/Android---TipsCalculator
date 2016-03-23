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
    }

    public void calculateTips(View view) {
        TextView billResultTV = (TextView) findViewById(R.id.lbl_bill_result);
        TextView tipResultTV = (TextView) findViewById(R.id.lbl_tip_result);
        TextView totalResultTV = (TextView) findViewById(R.id.lbl_total_result);

        try {
            double inTotal = Double.parseDouble(((EditText) findViewById(R.id.in_total)).getText().toString());
            int inNumPerson = (int) Double.parseDouble(((EditText) findViewById(R.id.in_num_person)).getText().toString());
            double inTipPercent = Double.parseDouble(((EditText) findViewById(R.id.in_tip_percent)).getText().toString());

            double tipResult, totalResult, billResult;
            if (inNumPerson < 1 || inTotal <= 0 || inTipPercent < 0) {
                tipResultTV.setText("Invalid values at Total, Number of person, or Tip percentage");
                totalResultTV.setText("Please try again");
            } else {
                billResult = inTotal / inNumPerson;
                tipResult = billResult * (inTipPercent / 100);
                totalResult = billResult + tipResult;
                // reset the number of person
                ((EditText) findViewById(R.id.in_num_person)).setText(Integer.toString(inNumPerson));
                // display the output
                TextView resultText = (TextView) findViewById(R.id.lbl_result);
                if (inNumPerson == 1)
                    resultText.setText(getString(R.string.resultSingle));
                else
                    resultText.setText(getString(R.string.resultMulti));

                String billResultText = String.format("$%.2f", billResult);
                billResultTV.setText(billResultText);

                String tipResultText = String.format("Tip:\t\t$%.2f", tipResult);
                tipResultTV.setText(tipResultText);

                String totalResultText = String.format("Total:\t\t$%.2f", totalResult);
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
