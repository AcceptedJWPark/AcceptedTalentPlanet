package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2018-03-09.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.accepted.acceptedtalentplanet.Alarm.ListItem;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    private String alarmType = null;
    private String alarmTxt = null;
    public static int countAlarmPush_Message = 0;
    public static int countAlarmPush_Qna = 0;
    public static int countAlarmPush_Claim = 0;
    private int isReadMessage;
    private int isReadQna;
    private int isReadClaim;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        ArrayList<ListItem> arrayList = SaveSharedPreference.getPrefAlarmArry(getApplicationContext());
        if (arrayList == null) {
            arrayList = new ArrayList<>();

        }

        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        Intent intent1 = null;
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Log.d(TAG, "Message content: " + remoteMessage.getData().get("message"));
            switch (remoteMessage.getData().get("type")) {
                case "Message":
                    arrayList.add(new ListItem(R.drawable.testpicture, "김대지", "2016.10.04 09:51", 6, R.drawable.icon_delete, false));
                    countAlarmPush_Message++;
                    alarmType = "Message";
                    alarmTxt = "새로운 메세지 " +countAlarmPush_Message + "개 있습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity.class);
                    intent1.putExtra("alarmType", "Message");
                    SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                    break;
                case "QNA":
                    arrayList.add(new ListItem("2016.11.03 15:41", 4, R.drawable.icon_delete, false));
                    countAlarmPush_Qna++;
                    alarmType = "QNA";
                    alarmTxt = "Q&A 답변완료 " + countAlarmPush_Qna + "건이 있습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity.class);
                    intent1.putExtra("alarmType", "QNA");
                    SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                    break;
                case "Claim":
                    arrayList.add(new ListItem("2016.12.01 17:05", 5, R.drawable.icon_delete, false));
                    countAlarmPush_Claim++;
                    alarmType = "Claim";
                    alarmTxt = "신고하기 조치완료 "+ countAlarmPush_Claim + "건이 있습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList.MainActivity.class);
                    intent1.putExtra("alarmType", "Claim");
                    SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                    break;
            }

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }
        }

        isReadMessage = countAlarmPush_Message > 0 ? 1 : 0;
        isReadQna = countAlarmPush_Qna > 0 ?  1 : 0;
        isReadClaim = countAlarmPush_Claim > 0 ?  1 : 0;

        if(isReadMessage+isReadQna+isReadClaim > 1)
        {
            alarmTxt = "새로운 알림 " + String.valueOf(countAlarmPush_Message + countAlarmPush_Qna + countAlarmPush_Claim) + "건이 있습니다.";
            intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Alarm.MainActivity.class);
            intent1.putExtra("alarmType", "Alarm");
        }




        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icon_friendadd_unclicked)
            .setContentTitle(alarmTxt)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(new long[]{1, 1000})
            .setWhen(System.currentTimeMillis());
            mBuilder.setContentIntent(contentIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder.build());

        Log.d(String.valueOf(remoteMessage.getData().size()), "countAlarm = ");

    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
//        // [START dispatch_job]
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
//        Job myJob = dispatcher.newJobBuilder()
//                .setService(MyJobService.class)
//                .setTag("my-job-tag")
//                .build();
//        dispatcher.schedule(myJob);
//        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
//                        .setContentTitle("FCM Message")
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
