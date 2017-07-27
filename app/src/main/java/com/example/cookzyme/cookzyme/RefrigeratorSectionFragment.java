package com.example.cookzyme.cookzyme;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cookzyme.cookzyme.customAdapter.customAdapterRefrigerator;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.example.cookzyme.cookzyme.module.Receiver;
import com.example.cookzyme.cookzyme.module.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.cookzyme.cookzyme.ref.letmetry;

public class RefrigeratorSectionFragment extends Fragment {

    static com.example.cookzyme.cookzyme.customAdapter.customAdapterRefrigerator customAdapterRefrigerator;
    ListView listView;
    FloatingActionButton addIngredient;
    ImageButton cooking;
    static ArrayList<Ingredients> refrigerator;
    View rootView;
    Toolbar Toolbar;
    Menu menu;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        }
    };

    public static RefrigeratorSectionFragment newInstance() {
        RefrigeratorSectionFragment fragment = new RefrigeratorSectionFragment();
        return fragment;
    }

    public RefrigeratorSectionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.refrigerator_section_fragment, container, false);

        listView=(ListView) rootView.findViewById(R.id.refrigeratorListView);
        addIngredient=(FloatingActionButton) rootView.findViewById(R.id.addIngredient);
        cooking=(ImageButton) rootView.findViewById(R.id.cooking);
        Toolbar=(Toolbar) rootView.findViewById(R.id.Toolbar);
        //to calling onCreateOptionsMenu
        setHasOptionsMenu(true);
        Toolbar.inflateMenu(R.menu.refrigerator_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(Toolbar);

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), addIngredient.class);
                startActivityForResult(in,0);
            }
        });
        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ClickHat");
                CanCookFragment canCook = new CanCookFragment();

                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.refrigerator, canCook);
                transaction.addToBackStack(null);
                transaction.commit();
//                SQLiteDBHelper database = new SQLiteDBHelper(getContext());
//                database.getReadableDatabase().execSQL("delete from "+ SQLiteDBHelper.REFRIGERATOR_TABLE_NAME);
//                database.closeDB();
//                refrigerator=null;
//                listView.setAdapter(null);

            }
        });

        customAdapterRefrigerator = new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),R.layout.singlerow_refrigerator,this);
        listView.setAdapter(customAdapterRefrigerator);

        if(refrigerator==null){
            refrigerator=new ArrayList<>();

            SQLiteDBHelper database = new SQLiteDBHelper(getContext());
            refrigerator=database.getAllRefrigerator();
            database.closeDB();
                if(refrigerator.size()>0){
                    customAdapterRefrigerator.addAll(refrigerator);

                    ArrayList<String> ingrementExpireToday=new ArrayList<>();

                    for (Ingredients i:refrigerator) {
                        Date date = new Date();
                        date.setHours(0);
                        date.setMinutes(0);
                        date.setSeconds(0);
                        long expire = i.getExpire().getTime()/(24*60*60*1000);
                        long today = date.getTime()/(24*60*60*1000);
                        if(expire-today==0){
                            ingrementExpireToday.add(i.getIngredient_name());
                        }
                    }

                    if(ingrementExpireToday.size()>0){
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(getContext())
                                        .setSmallIcon(R.drawable.ic_launcher)
                                        .setContentTitle("The ingredient has expire, Today!")
                                        .setContentText("Checking the recipe.");
                        NotificationCompat.InboxStyle inboxStyle =
                                new NotificationCompat.InboxStyle();
                        inboxStyle.setBigContentTitle("List of ingredient That expire Today:");
                        for (int i=0; i < ingrementExpireToday.size(); i++) {

                            inboxStyle.addLine(ingrementExpireToday.get(i));
                        }
                        mBuilder.setStyle(inboxStyle);

                        NotificationManager mNotificationManager =
                                (NotificationManager) getContext().getSystemService(getContext().getApplicationContext().NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder.build());
                    }

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 6);
                    calendar.set(Calendar.MINUTE, 0);

                    Intent myIntent = new Intent(getContext(), Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(getContext().ALARM_SERVICE);
                    alarmManager.setInexactRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);

                }
        }

        //listView.setOnItemClickListener(onItemClickListener);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refrigerator_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        this.menu=menu;
        if(customAdapterRefrigerator.getCount()>0 && ((LinearLayout) listView.getChildAt(0)).getChildAt(0).getVisibility()==View.VISIBLE){
            for (int i=0;i<listView.getCount();i++) {
                ((LinearLayout) listView.getChildAt(i)).getChildAt(0).setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.menu=menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_refrigerator:
                item.setVisible(false);
                menu.getItem(1).setVisible(true);
                for (int i=0;i<listView.getCount();i++) {
                    ((LinearLayout) listView.getChildAt(i)).getChildAt(0).setVisibility(View.VISIBLE);
                }
                return true;
            case R.id.cancel_refrigerator:
                item.setVisible(false);
                menu.getItem(0).setVisible(true);
                for (int i=0;i<listView.getCount();i++) {
                    ((LinearLayout) listView.getChildAt(i)).getChildAt(0).setVisibility(View.GONE);
                }
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == getActivity().RESULT_OK) {
                SQLiteDBHelper database = new SQLiteDBHelper(getContext());
                refrigerator=database.getAllRefrigerator();
                database.closeDB();
//                getIngredientPic();
//                customAdapterRefrigerator =new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),R.layout.singlerow_refrigerator,this,refrigerator);
//                listView.setAdapter(customAdapterRefrigerator);
                customAdapterRefrigerator.clear();
                customAdapterRefrigerator.addAll(refrigerator);
                customAdapterRefrigerator.notifyDataSetChanged();
            }
        }
    }

    public void removeItem(Ingredients ingredient){
        refrigerator.remove(ingredient);
        SQLiteDBHelper database = new SQLiteDBHelper(getContext());
        database.removeRefrigerator(ingredient);
        database.closeDB();
        customAdapterRefrigerator.remove(ingredient);
    }

    public ArrayList<Ingredients> getRefrigerator(){
        return refrigerator;
    }

    //    private void getIngredientPic(){
//        new AsyncTask<Void, Void, List<BitmapDrawable>>() {
//            @Override
//            protected List<BitmapDrawable> doInBackground(Void... params) {
//                try {
//                    List<Ingredients> results = AzureServiceAdapter.getInstance().getClient().getTable(Ingredients.class).execute().get();
//                    List<Ingredients> result = results;
//                    for (Ingredients i:refrigerator
//                            ) {
//                        for (Ingredients iresult:result
//                                ) {
//                            if(i.getIngredient_name().equals(iresult.getIngredient_name())){
//                                i.setPath(iresult.getPath());
//                                break;
//                            }
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (MobileServiceException e) {
//                    e.printStackTrace();
//                }
//                List<BitmapDrawable> foodPic = new ArrayList<BitmapDrawable>();
//                for (Ingredients i:refrigerator
//                        ) {
//                    URL newurl = null;
//                    try {
//                        newurl = new URL(i.getPath());
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap mIcon_val = null;
//                    try {
//                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
//                    foodPic.add(ob);
//                }
//
//                return foodPic;
//            }
//
//            @Override
//            protected void onPostExecute(List<BitmapDrawable> aVoid) {
//                super.onPostExecute(aVoid);
//                customAdapterRefrigerator =new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),refrigerator);
//                customAdapterRefrigerator.setFoodPic(aVoid);
//                listView.setAdapter(customAdapterRefrigerator);
//            }
//        }.execute();
//    }

}
