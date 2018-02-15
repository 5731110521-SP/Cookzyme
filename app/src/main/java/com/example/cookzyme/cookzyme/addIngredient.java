package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.database.Ingredients;
import com.example.cookzyme.cookzyme.module.SQLiteDBHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class addIngredient extends AppCompatActivity {
    private Button mDateButton;
    private Button confirmButton;
    private Calendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
    private TextView clickHere;
    TextView foodName;
    EditText num;
    Spinner pronounSpinner;
    Button datePicker;
    ImageView foodPic;
    private Uri imageUri;
    private static final int CAMERA_REQUEST = 1888;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    Thread t;
    Bitmap thumbnail;
    ProgressBar progressBar;
    Button find;
    TextView or;
    String ans = "";
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        foodName=(TextView) findViewById(R.id.foodName);
        num=(EditText) findViewById(R.id.num);
        pronounSpinner=(Spinner) findViewById(R.id.pronounSpinner);
        datePicker=(Button) findViewById(R.id.datePicker);
        foodPic=(ImageView) findViewById(R.id.foodPic);
        progressBar=(ProgressBar) findViewById(R.id.progressBarAdd);
        find=(Button)findViewById(R.id.find);
        or=(TextView)findViewById(R.id.or);

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
//                DateFormat df2 = new SimpleDateFormat("M-d-yyyy");
//                try {
//                    Ingredients ingredient;
                    new getIngredientPicTask(v.getContext()).execute(foodName.getText().toString());
//                    if(pronounSpinner.getSelectedItem().toString().equals("-")){
//                        ingredient = new Ingredients(foodName.getText().toString(), path, "", 0
//                                , df2.parse(datePicker.getText().toString()));
//                    }else if(num.getText().length()==0){
//                        num.setError("กรุณากรอกจำนวน");
//                        num.requestFocus();
//                        return;
//                    }else {
//                        ingredient = new Ingredients(foodName.getText().toString(), path, pronounSpinner.getSelectedItem().toString(),
//                                Integer.parseInt(num.getText().toString())
//                                , df2.parse(datePicker.getText().toString()));
//                    }
//                    SQLiteDBHelper database = new SQLiteDBHelper(v.getContext());
//                    database.insertRefrigerator(ingredient);
//                    database.closeDB();
//                    setResult(RESULT_OK);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                    finish();
            }
        });
        // all about click here
        clickHere = (TextView) findViewById(R.id.clickHere);
        clickHere.setPaintFlags(clickHere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        findViewById(R.id.clickHere).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent loginIntent = new Intent(addIngredient.this, WrongIngredient.class);
                Bundle b = new Bundle();
                JSONObject obj = null;
                try {
                    obj = new JSONObject(ans);
                    JSONArray jobj = obj.getJSONArray("Predictions");
                    b.putString("item1", ((JSONObject)jobj.get(1)).getString("Tag"));
                    b.putString("item2", ((JSONObject)jobj.get(2)).getString("Tag"));
                    b.putString("item3", ((JSONObject)jobj.get(3)).getString("Tag"));
                    b.putString("item4", ((JSONObject)jobj.get(4)).getString("Tag"));
                    loginIntent.putExtras(b);
                    startActivityForResult(loginIntent,0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        foodPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodName.setVisibility(View.GONE);
                findViewById(R.id.addlinear1).setVisibility(View.GONE);
                findViewById(R.id.addlinear2).setVisibility(View.GONE);
                findViewById(R.id.addlinear3).setVisibility(View.GONE);
                findViewById(R.id.addlinear4).setVisibility(View.GONE);
                foodPic.setVisibility(View.GONE);

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                if (checkPermissionWRITE_EXTERNAL_STORAGE(view.getContext())) {
                    imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                }else{
                    System.out.println("******************************************");
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

    }


    public boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    new ImageTask(this).execute();
                }
                return;
            case 0:
                if (resultCode == Activity.RESULT_OK){
                    String name = data.getStringExtra("name");
                    foodName.setText(name);
                }
        }
    }

    private class CustomVisonTask extends AsyncTask<Bitmap,Void,String> {
        Context context;

        public CustomVisonTask(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Bitmap... bitmap) {
            HttpClient httpclient = HttpClients.createDefault();
            String answer = "";
            try
            {
                URIBuilder builder = new URIBuilder("https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/e2677f6f-0e6c-437e-96f0-5d3baaf9bc4b/image");

                builder.setParameter("iterationId", "4744e864-1cae-47fc-92cc-1cd9a2af6222");
                URI uri = builder.build();
                HttpPost request = new HttpPost(uri);
                request.setHeader("Content-Type", "multipart/form-data");
                request.setHeader("Prediction-key", "14adf81076e843c3a06f6f325337f291");

                // Request body
                //System.out.println(getEncoded64ImageStringFromBitmap(bitmap));
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                Bitmap b = getResizedBitmap(bitmap[0],300);
                ByteArrayEntity reqEntity = new ByteArrayEntity( getEncoded64ImageStringFromBitmap(b));
                b.recycle();
                request.setEntity(reqEntity);
                System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
                HttpResponse response = httpclient.execute(request);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                HttpEntity entity = response.getEntity();
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                if (entity != null)
                {
                    ans = EntityUtils.toString(entity);
                    JSONObject obj = new JSONObject(ans);
                    JSONArray jobj = obj.getJSONArray("Predictions");
                    answer=((JSONObject)jobj.get(0)).getString("Tag");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return answer;
        }

        protected void onPostExecute(String result) {
            foodName.setText(result);

            progressBar.setVisibility(View.GONE);
            foodName.setVisibility(View.VISIBLE);
            findViewById(R.id.addlinear1).setVisibility(View.VISIBLE);
            findViewById(R.id.addlinear2).setVisibility(View.VISIBLE);
            findViewById(R.id.addlinear3).setVisibility(View.VISIBLE);
            findViewById(R.id.addlinear4).setVisibility(View.VISIBLE);
            List<String> list = new ArrayList<>();
            list.add("ฟอง");
            list.add("-");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            pronounSpinner.setAdapter(dataAdapter);
        }


        public byte[] getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteFormat = stream.toByteArray();
            return byteFormat;
        }
        public Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleWidth);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);

            //bm.recycle();
            return resizedBitmap;
        }
    }

    private class ImageTask extends AsyncTask<Void,Void,Void>{
        Context context;

        public ImageTask(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            or.setVisibility(View.GONE);
            find.setVisibility(View.GONE);
            foodPic.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            foodPic.setVisibility(View.VISIBLE);
            foodPic.setImageBitmap(thumbnail);
            Bitmap b = thumbnail;
            //new CustomVisonTask(context).execute(b);
        }
    }

    private class getIngredientPicTask extends AsyncTask<String,Void,String>{
        Context context;
        public getIngredientPicTask(Context context) {
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            findViewById(R.id.addlinear3).setVisibility(View.GONE);
            findViewById(R.id.addlinear4).setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            List<Ingredients> results=new ArrayList<>();
            try {
                results = AzureServiceAdapter.getInstance().getClient().getTable(Ingredients.class)
                        .where().field("ingredient_name").eq(val(params[0])).execute().get();
                return results.get(0).getPath();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                Ingredients ingredient;
                DateFormat df2 = new SimpleDateFormat("M-d-yyyy");
                if (pronounSpinner.getSelectedItem().toString().equals("-")) {
                    ingredient = new Ingredients(foodName.getText().toString(), aVoid, "", 0
                            , df2.parse(datePicker.getText().toString()));
                } else if (num.getText().length() == 0) {
                    num.setError("กรุณากรอกจำนวน");
                    num.requestFocus();
                    return;
                } else {
                    ingredient = new Ingredients(foodName.getText().toString(), aVoid, pronounSpinner.getSelectedItem().toString(),
                            Integer.parseInt(num.getText().toString())
                            , df2.parse(datePicker.getText().toString()));
                }
                SQLiteDBHelper database = new SQLiteDBHelper(context);
                database.insertRefrigerator(ingredient);
                database.closeDB();

                setResult(RESULT_OK);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
