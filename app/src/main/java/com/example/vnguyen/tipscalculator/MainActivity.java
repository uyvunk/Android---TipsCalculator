package com.example.vnguyen.tipscalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the ad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setupUI(this.findViewById(R.id.parent));
    }

    public void calculateTips(View view) {
        try {
            double inTotal = Double.parseDouble(((EditText) findViewById(R.id.in_total)).getText().toString());
            int inNumPerson = (int) Double.parseDouble(((EditText) findViewById(R.id.in_num_person)).getText().toString());
            double inTipPercent = Double.parseDouble(((EditText) findViewById(R.id.in_tip_percent)).getText().toString());

            double tipResult, totalResult, billResult;
            if (inNumPerson < 1 || inTotal <= 0 || inTipPercent < 0) {
                displayError();
            } else {
                billResult = inTotal / inNumPerson;
                tipResult = billResult * (inTipPercent / 100);
                totalResult = billResult + tipResult;
                // reset the number of person
                ((EditText) findViewById(R.id.in_num_person)).setText(Integer.toString(inNumPerson));
                // display the output
                TextView resultText = (TextView) findViewById(R.id.lbl_result);
                TextView billResultTV = (TextView) findViewById(R.id.lbl_bill_result);
                TextView tipResultTV = (TextView) findViewById(R.id.lbl_tip_result);
                TextView totalResultTV = (TextView) findViewById(R.id.lbl_total_result);

                if (inNumPerson == 1)
                    resultText.setText(getString(R.string.resultSingle));
                else
                    resultText.setText(getString(R.string.resultMulti));

                String billResultText = String.format("Bill:\t\t$%.2f", billResult);
                billResultTV.setText(billResultText);

                String tipResultText = String.format("Tip:\t\t$%.2f", tipResult);
                tipResultTV.setText(tipResultText);

                String totalResultText = String.format("Total:\t\t$%.2f", totalResult);
                totalResultTV.setText(totalResultText);
            }
        } catch (Exception e) {
            displayError();
        }
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void displayError() {
        TextView billResultTV = (TextView) findViewById(R.id.lbl_bill_result);
        TextView tipResultTV = (TextView) findViewById(R.id.lbl_tip_result);
        TextView totalResultTV = (TextView) findViewById(R.id.lbl_total_result);
        TextView resultText = (TextView) findViewById(R.id.lbl_result);
        // clear all the TextView
        resultText.setText("Invalid values at Total, " +
                "Number of person, or Tip percentage. Please try again");
        billResultTV.setText("");
        tipResultTV.setText("");
        totalResultTV.setText("");
    }

    public void getTipPercent(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int checkedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = (RadioButton) findViewById(checkedRadioButtonID);
        String tipVal = checkedRadioButton.getText().toString().replace("%","");
        // set the checked value to the tip EditText
        EditText et = (EditText) findViewById(R.id.in_tip_percent);
        et.setText(tipVal);
    }
}
