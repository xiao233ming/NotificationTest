package com.example.mingming.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int NOTIFICATION_FLAG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notificationMethod(View view){
        //在Android进行通知处理，首先需要从系统中获得通知管理器
        //NotificationManager，它是一个系统Service。
        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        switch (view.getId()){
            //默认通知
            case R.id.btn1:
                // 创建一个PendingIntent，和Intent类似，
                // 不同的是由于不是马上调用，需要在下拉状态条出发的activity，
                // 所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
                //下面需兼容Android 2.x版本是的处理方式

                /*Notification notify1 = new Notification(R.mipmap.ic_launcher,
                        "TickerText" + "您有新短消息，请注意查收！",System.currentTimeMillis());*/


         /*     Notification notify1 = new Notification();
                notify1.icon = R.mipmap.ic_launcher;
                notify1.tickerText = "TickerText:您有新短消息，请注意查收！";
                notify1.when = System.currentTimeMillis();
                notify1.setLatestEventInfo(this, "Notification Title",
                        "This is the notification message", pendingIntent);
                notify1.number = 1;
                //FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
                notify1.flags = Notification.FLAG_AUTO_CANCEL;
                // 通过通知管理器来发起通知。
                // 如果id不同，则每click，在statu那里增加一个提示
                manager.notify(NOTIFICATION_FLAG,notify1);*/
                break;

            case R.id.btn2:
                PendingIntent pendingIntent2 = PendingIntent.getActivity
                        (this,0,new Intent(this,MainActivity.class),0);
                // 通过Notification.Builder来创建通知，注意API Level
                // API11之后才支持
                Notification notify2 = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Title")// 设置在下拉status
                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                        .setContentText("This is the notification2 message")// TextView中显示的详细内容
                        .setContentIntent(pendingIntent2) // 关联PendingIntent
                        .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。
                        // 这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                        .getNotification(); // 需要注意build()是在API level
                // 16及之后增加的，在API11中可以使用getNotificatin()来代替
                notify2.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(NOTIFICATION_FLAG, notify2);
                break;

            // 默认通知 API16及之后可用
            case R.id.btn3:
                PendingIntent pendingIntent3 = PendingIntent.getActivity
                        (this,0,new Intent(this,MainActivity.class),0);
                //通过Notification.Builder来创建通知，注意API Level
                //API16之后才支持
                Notification notify3 = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("TickerText"+"您有新短消息，请注意查收")
                        .setContentTitle("Notification Title")
                        .setContentText("This is the notification3 message")
                        // 需要注意build()是在API16添加的
                        .setContentIntent(pendingIntent3).setNumber(1).build();
                notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
                // 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
                manager.notify(NOTIFICATION_FLAG, notify3);
                break;
            case R.id.btn4:
                break;

            case R.id.btn5:
                break;
        }
    }
}
