package com.example.cookzyme.cookzyme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    TextView foodName;
    EditText num;
    TextView pronoun;
    Button datePicker;
    ImageView foodPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        foodName=(TextView) findViewById(R.id.foodName);
        num=(EditText) findViewById(R.id.num);
        pronoun=(TextView) findViewById(R.id.pronoun);
        datePicker=(Button) findViewById(R.id.datePicker);
        foodPic=(ImageView) findViewById(R.id.foodPic);

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
                finish();
            }
        });
        //confirm button
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DateFormat df2 = new SimpleDateFormat("M-d-yyyy");
                try {
                    Ingredients ingredient = new Ingredients(foodName.getText().toString(),"path",pronoun.getText().toString(),
                            Integer.parseInt(num.getText().toString())
                            ,df2.parse(datePicker.getText().toString()));
                    SQLiteDBHelper database = new SQLiteDBHelper(v.getContext());
                    database.insertRefrigerator(ingredient);
                    database.closeDB();
                    setResult(RESULT_OK);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                    finish();
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
