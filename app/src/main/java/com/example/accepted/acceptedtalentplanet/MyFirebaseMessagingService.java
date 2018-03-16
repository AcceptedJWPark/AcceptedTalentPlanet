package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2018-03-09.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.accepted.acceptedtalentplanet.Alarm.ListItem;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    private String alarmType = null;
    private String alarmTxt = null;
    public static int countAlarmPush_Message = 0;
    public static int countAlarmPush_Qna = 0;
    public static int countAlarmPush_Claim = 0;
    private boolean messagePushGrant;
    private boolean conditionPushGrant;
    private boolean answerPushGrant;
    private int isReadMessage;
    private int isReadQna;
    private int isReadClaim;


    private Intent intent1 = null;

    private String datas = null;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        messagePushGrant = SaveSharedPreference.getMessagePushGrant(getApplicationContext());
        conditionPushGrant = SaveSharedPreference.getConditionPushGrant(getApplicationContext());
        answerPushGrant = SaveSharedPreference.getAnswerPushGrant(getApplicationContext());


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
        if (remoteMessage.getData().size() > 0) {
            //Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            //Log.d(TAG, "Message content: " + remoteMessage.getData().get("message"));

            if(remoteMessage.getData().get("type").equals("Message"))
            {
                getMessage(remoteMessage.getData().get("datas"));
            }

            datas = remoteMessage.getData().get("datas");



            addNotificationList(remoteMessage.getData().get("type"));
            addAlarmList(remoteMessage.getData().get("type"));

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }
        }
        if (messagePushGrant || conditionPushGrant || answerPushGrant) {

            isReadMessage = countAlarmPush_Message > 0 ? 1 : 0;
            isReadQna = countAlarmPush_Qna > 0 ? 1 : 0;
            isReadClaim = countAlarmPush_Claim > 0 ? 1 : 0;


            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            }

            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.

            if (isReadMessage + isReadQna + isReadClaim > 1) {
                alarmTxt = "새로운 알림 " + String.valueOf(countAlarmPush_Message + countAlarmPush_Qna + countAlarmPush_Claim) + "건이 있습니다.";
                intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Alarm.MainActivity.class);
                intent1.putExtra("alarmType", "Alarm");
            }

            if (intent1 != null) {

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icon_friendadd_clicked)
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
        }

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


    private void addAlarmList(String type){
        ArrayList<ListItem> arrayList = SaveSharedPreference.getPrefAlarmArry(getApplicationContext());
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }

        String userName = null;
        String userId = null;
        String unformatedDate = null;
        int talentID = -1;
        int talentType = -1;
        int roomId = -1;

        switch (type){
            case "Message": {
                try {
                    JSONObject obj = new JSONObject(datas);
                    userName = obj.getString("USER_NAME");
                    userId = obj.getString("USER_ID");
                    unformatedDate = obj.getString("CREATION_DATE_STRING");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                String formattedDate = dateFormat(unformatedDate);

                int countMessage = 1;
                ListItem listItem = new ListItem(R.drawable.testpicture, userName, "새로운 메세지 1건이 있습니다.", 1, formattedDate, 6, R.drawable.icon_delete, false);
                listItem.setUserId(userId);
                listItem.setUserName(userName);
                arrayList.add(0, listItem);

                for (int i = 1; i < arrayList.size(); i++) {
                    if (arrayList.get(0).getUserId().equals(arrayList.get(i).getUserId())) {
                        countMessage = arrayList.get(i).getCountMessage() + 1;
                        arrayList.remove(i);
                        arrayList.remove(0);
                        listItem.settxt("새로운 메세지 " + countMessage + "건이 있습니다.");
                        listItem.setCountMessage(countMessage);
                        arrayList.add(0, listItem);
                    }
                }
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                break;
            }
            case "QNA": {

                try {
                    JSONObject obj = new JSONObject(datas);
                    unformatedDate = obj.getString("CREATION_DATE_STRING");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                String formatedDate = dateFormat(unformatedDate);

                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, "Talent Planet", alarmTxt, formatedDate, 4, R.drawable.icon_delete, false));
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                Log.d("lastDate = ", unformatedDate);
                break;
            }
            case "Claim": {

                try {
                    JSONObject obj = new JSONObject(datas);
                    unformatedDate = obj.getString("CREATION_DATE_STRING");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                String formatedDate = dateFormat(unformatedDate);

                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, "Talent Planet", alarmTxt, formatedDate, 5, R.drawable.icon_delete, false));
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                break;
            }
            case "Interest": {
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, userName, talentID, "Talent Planet", unformatedDate, 1, talentType, R.drawable.icon_delete, false));
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                break;
            }
        }
    }

    private void addNotificationList(String type){
        intent1 = null;
        switch (type){
            case "Message":
                if(messagePushGrant) {
                    countAlarmPush_Message++;
                    alarmType = "Message";
                    alarmTxt = "새로운 메세지 " + countAlarmPush_Message + "건이 도착했습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity.class);
                    intent1.putExtra("alarmType", "Message");
                }
                break;
            case "QNA":
                if(answerPushGrant) {
                    countAlarmPush_Qna++;
                    alarmType = "QNA";
                    alarmTxt = "문의하신 Q&A " + countAlarmPush_Qna + "건이 답변 완료되었습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList.MainActivity.class);
                    intent1.putExtra("alarmType", "QNA");
                }
                break;
            case "Claim":
                if(answerPushGrant) {
                    countAlarmPush_Claim++;
                    alarmType = "Claim";
                    alarmTxt = "신고하기 " + countAlarmPush_Claim + "건이 조치 완료되었습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList.MainActivity.class);
                    intent1.putExtra("alarmType", "Claim");
                }
                break;
            case "Interest":
                alarmType = "Interest";
                alarmTxt = "받은 관심이 있습니다.";
                intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                intent1.putExtra("alarmType", alarmType);
                break;
        }
    }

    private void getMessage(String datas){
        try {
            JSONObject obj = new JSONObject(datas);
            String dbName = "/accepted.db";
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);

            Log.d("TAG", "Datas: " + datas.toString());

            int roomID = SaveSharedPreference.makeChatRoom(getApplicationContext(), obj.getString("USER_ID"), obj.getString("USER_NAME"));
            sqLiteDatabase.execSQL("INSERT INTO TB_CHAT_LOG(MESSAGE_ID, ROOM_ID, USER_ID, CONTENT, CREATION_DATE, READED_FLAG) VALUES (" + obj.getString("MESSAGE_ID") + ", " + roomID + ", '" + obj.getString("USER_ID") + "','" + obj.getString("CONTENT").replace("'", "''") + "','" + obj.getString("CREATION_DATE_STRING") + "', 'N')");
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private String dateFormat(String lastDate)
    {
        Date tempDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
        String nowDateStr = sdf.format(tempDate);
        String[] nowDateTemp = nowDateStr.split(",");
        String nowDate = nowDateTemp[0];

        String[] dateTemp = lastDate.split(",");
        lastDate = dateTemp[0];
        String dateTime = dateTemp[1].substring(0, 8);
        lastDate = (lastDate.equals(nowDate)) ? dateTime : lastDate;

        return lastDate;
    }


}
