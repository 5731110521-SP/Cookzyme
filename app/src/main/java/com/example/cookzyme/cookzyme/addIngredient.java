package com.example.cookzyme.cookzyme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.graphics.Color;
import android.widget.TextView;

import java.util.Calendar;

public class addIngredient extends AppCompatActivity {
    private Button mDateButton;
    private Button confirmButton;
    private Calendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
    private Context context;
    private TextView clickHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        // change color button
        confirmButton = (Button) findViewById(R.id.confirm);
        int colorButton = getResources().getColor(R.color.colorButton);
        GradientDrawable sd = (GradientDrawable) confirmButton.getBackground();
        sd.setColor(colorButton);

        // date picker
        mDateButton = (Button) findViewById(R.id.datePicker);
        mCalendar = Calendar.getInstance();

        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate();

        //cancel button
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent loginIntent = new Intent(addBankaccount.this, viewBankAccount.class);
//                startActivity(loginIntent);
            }
        });
        //confirm button
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent loginIntent = new Intent(addBankaccount.this, viewBankAccount.class);
//                startActivity(loginIntent);
            }
        });
        // all about click here
        clickHere = (TextView) findViewById(R.id.clickHere);
        clickHere.setPaintFlags(clickHere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        findViewById(R.id.clickHere).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent loginIntent = new Intent(addBankaccount.this, viewBankAccount.class);
//                startActivity(loginIntent);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateCurrentDate() {
        mDateButton.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateCurrentDate();
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
