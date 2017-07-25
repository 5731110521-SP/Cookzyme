package com.example.cookzyme.cookzyme.module;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.ArrayAdapter;

import com.example.cookzyme.cookzyme.RefrigeratorSectionFragment;
import com.example.cookzyme.cookzyme.Splash;
import com.example.cookzyme.cookzyme.addIngredient;
import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.database.Ingredients;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AlarmService extends Service
{

    private NotificationManager manager;
    Notification myNotication;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent2, int startId)
    {
        super.onStart(intent2, startId);

//        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
//        Intent intent1 = new Intent(this.getApplicationContext(),addIngredient.class);

//        Notification notification = new Notification(R.drawable.ic_launcher,"This is a test message!", System.currentTimeMillis());
//        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.setLatestEventInfo(this.getApplicationContext(), "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
//
//        mManager.notify(0, notification);

        //2

//        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 1, intent, 0);
//
//        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
//
//        builder.setAutoCancel(false);
//        builder.setTicker("this is ticker text");
//        builder.setContentTitle("WhatsApp Notification");
//        builder.setContentText("You have a new message");
//        builder.setSmallIcon(R.drawable.ic_launcher);
//        builder.setContentIntent(pendingIntent);
//        builder.setOngoing(true);
//        builder.setSubText("This is subtext...");   //API level 16
//        builder.setNumber(100);
//        builder.build();
//
//        myNotication = builder.getNotification();
//        manager.notify(11, myNotication);

        ArrayList<Ingredients> refrigerator=new ArrayList<>();

        SQLiteDBHelper database = new SQLiteDBHelper(this);
        refrigerator=database.getAllRefrigerator();
        database.closeDB();

        ArrayList<String> s = new ArrayList<>();
        long less=Integer.MAX_VALUE;
        for (Ingredients i :refrigerator) {
            Date date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            long expire = i.getExpire().getTime()/(24*60*60*1000);
            long today = date.getTime()/(24*60*60*1000);
            if(expire-today<less && expire-today>0){
                less=expire-today;
                s.clear();
                s.add(i.getIngredient_name());
            }else if(expire-today==less){
                s.add(i.getIngredient_name());
            }
        }

        if(s.size()>0) {

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("The ingredient has expire in " + less + " days!")
                            .setContentText("Checking the recipe.");

            NotificationCompat.InboxStyle inboxStyle =
                    new NotificationCompat.InboxStyle();
            inboxStyle.setBigContentTitle("List of ingredient that has expire in " + less + " days:");
            for (int i = 0; i < s.size(); i++) {

                inboxStyle.addLine(s.get(i));
            }
            mBuilder.setStyle(inboxStyle);
            Intent resultIntent = new Intent(this, Splash.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.DAY_OF_MONTH,new Date().getDay());
//        calendar.set(Calendar.HOUR_OF_DAY, 6);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//
//        Intent myIntent = new Intent(this, Receiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,0);
//
//        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC,calendar.getTimeInMillis(), pendingIntent);

    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}