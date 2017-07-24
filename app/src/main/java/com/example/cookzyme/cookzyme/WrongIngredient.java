package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class WrongIngredient extends AppCompatActivity {

    LinearLayout linearitem1;
    LinearLayout linearitem2;
    LinearLayout linearitem3;
    LinearLayout linearitem4;

    LinearLayout select;

    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;

    Button Confirm;
    Button Cancel;
    Button Report;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            linearitem1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            linearitem2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            linearitem3.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            linearitem4.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            view.setBackgroundColor(getResources().getColor(R.color.lightGray));
            select=(LinearLayout)view;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_ingredient);

        linearitem1=(LinearLayout) findViewById(R.id.linearitem1);
        linearitem2=(LinearLayout) findViewById(R.id.linearitem2);
        linearitem3=(LinearLayout) findViewById(R.id.linearitem3);
        linearitem4=(LinearLayout) findViewById(R.id.linearitem4);

        item1=(TextView) findViewById(R.id.item1);
        item2=(TextView) findViewById(R.id.item2);
        item3=(TextView) findViewById(R.id.item3);
        item4=(TextView) findViewById(R.id.item4);

        Confirm=(Button) findViewById(R.id.ConfirmWrong);
        Cancel=(Button) findViewById(R.id.CancelWrong);
        Report=(Button) findViewById(R.id.ReportWrong);

        Bundle b = getIntent().getExtras();
        item1.setText(b.getString("item1"));
        item2.setText(b.getString("item2"));
        item3.setText(b.getString("item3"));
        item4.setText(b.getString("item4"));

        linearitem1.setOnClickListener(onClickListener);
        linearitem2.setOnClickListener(onClickListener);
        linearitem3.setOnClickListener(onClickListener);
        linearitem4.setOnClickListener(onClickListener);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v = (TextView) select.getChildAt(1);
                Intent data = new Intent();
                data.putExtra("name",v.getText());
                setResult(RESULT_OK, data);
                finish();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
