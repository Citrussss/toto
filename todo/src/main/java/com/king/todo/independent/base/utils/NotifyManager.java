package com.king.todo.independent.base.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.binding.model.App;
import com.king.todo.R;
import com.king.todo.ui.home.HomeActivity;
import com.union.bangbang.zero.AppUtil;

import java.lang.ref.WeakReference;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/22 8:56 PM
 * 只有编译器可能不骗你。
 */
public class NotifyManager {
    private NotificationManager manager;
    private String CHANNEL_ID = "qiqi_channel";
    private WeakReference<Context> context ;
    private String title;
    private String content;
    private int i=0;
    public NotifyManager(Context context) {
        this.context = new WeakReference<>(context);
        manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }
    public void sendNotify(){
        Intent intent1 = new Intent(AppUtil.peekActivity(), HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.get(), 1, intent1, 0);
        sendNotify(title,content, i++,pendingIntent);
    }
    public void sendNotify(String title, String content, int id, PendingIntent pendingIntent) {
        if (manager == null) return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && manager.getNotificationChannel(CHANNEL_ID) == null) {
            CharSequence name = "qiqi_channel";
            String Description = "This is qiqi channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.WHITE);
            mChannel.enableVibration(false);
            mChannel.setVibrationPattern(null);//震动
            mChannel.setShowBadge(false);
            manager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(AppUtil.peekActivity(), CHANNEL_ID);
        mBuilder.setContentTitle(title)
                .setContentText(content+id)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("收到一条任务消息")
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)// 设置该通知优先级
                .setSmallIcon(R.mipmap.ic_launcher);
        Notification notify = mBuilder.build();
        manager.notify(id, notify);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
