package com.dartrix.proyectoagenda;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.Random;


public class Notification_reciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String[] consejos = {"Desarrolla tu creatividad con herramientas de estudio online", "Establece metas de estudio y crea un calendario de estudio flexible", "No tengas miedo de preguntar","Plantéate preguntas constantemente"};
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MenuActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //objeto ramdom para activar el array

        int random = (int) Math.round(Math.random()* 3 ) ;

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Consejos Agendarium")
                .setContentText(""+consejos[random])
                .setVibrate(new long[] { 1000, 700, 1000, 500, 1000 })
                .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" +context.getPackageName()+"/"+R.raw.not))
                .setAutoCancel(true);



                notificationManager.notify(100,builder.build());

    }



}
