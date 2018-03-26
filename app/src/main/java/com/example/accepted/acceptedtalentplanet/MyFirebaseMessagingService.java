package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2018-03-09.
 */

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.accepted.acceptedtalentplanet.Alarm.ListItem;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.example.accepted.acceptedtalentplanet.Messanger.Chatting.MainActivity.receiverID;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    private String alarmType = null;
    private String alarmTxt = null;
    public static int countAlarmPush_Message = 0;
    public static int countAlarmPush_Qna = 0;
    public static int countAlarmPush_Claim = 0;
    public static int countAlarmPush_Intersting_Give = 0;
    public static int countAlarmPush_Intersting_Take = 0;
    public static int countAlarmPush_Condition = 0;
    public static int countAlarmPush_Cancel = 0;

    private boolean messagePushGrant;
    private boolean conditionPushGrant;
    private boolean answerPushGrant;

    private int isReadMessage;
    private int isReadQna;
    private int isReadInteresting_Give;
    private int isReadInteresting_Take;
    private int isReadClaim;
    private int isReadCondition;
    private int isReadCancel;


    private TimeZone time= TimeZone.getTimeZone("Asia/Seoul");

    private String topActivityName;


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

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = am.getRunningTasks(1);
        ComponentName topActivity = info.get(0).topActivity;
        topActivityName = topActivity.getClassName();


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
        Log.d(TAG, "Datas = " + remoteMessage.getData().get("datas"));

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            //Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            //Log.d(TAG, "Message content: " + remoteMessage.getData().get("message"));

            if (remoteMessage.getData().get("type").equals("Message")) {
                getMessage(remoteMessage.getData().get("datas"));
            }

            datas = remoteMessage.getData().get("datas");

            addNotificationList(remoteMessage.getData().get("type"));
            addAlarmList(remoteMessage.getData().get("type"));

            if (mMessageReceivedListener != null) {
                update();
            }

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
            isReadInteresting_Give = countAlarmPush_Intersting_Give > 0 ? 1 : 0;
            isReadInteresting_Take = countAlarmPush_Intersting_Take > 0 ? 1 : 0;
            isReadCondition = countAlarmPush_Condition > 0 ? 1 : 0;
            isReadCancel = countAlarmPush_Cancel > 0 ? 1 : 0;

            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            }

            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.

            if (isReadMessage + isReadQna + isReadClaim + isReadInteresting_Give + isReadInteresting_Take + isReadCondition + isReadCancel > 1) {
                alarmTxt = "새로운 알림 " + String.valueOf(countAlarmPush_Message + countAlarmPush_Qna + countAlarmPush_Claim + countAlarmPush_Intersting_Give + countAlarmPush_Intersting_Take + countAlarmPush_Condition + countAlarmPush_Cancel) + "건이 있습니다.";
                intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Alarm.MainActivity.class);
                intent1.putExtra("alarmType", "Alarm");
            }
            if (intent1 != null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder mBuilder;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("my_channel_0",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    mBuilder = new NotificationCompat.Builder(this, channel.getId());
                    Log.d("this is ", channel.getId());
                } else {
                    mBuilder = new NotificationCompat.Builder(this, "my_channel_0");
                }
                mBuilder = mBuilder.setSmallIcon(R.drawable.icon_friendadd_clicked)
                        .setContentTitle(alarmTxt)
                        .setAutoCancel(true)
                        .setVibrate(new long[]{1, 1000})
                        .setWhen(System.currentTimeMillis());
                mBuilder = mBuilder.setContentIntent(contentIntent);


                notificationManager.notify(1, mBuilder.build());



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
                if(arrayList.size()>0) {
                    arrayList.add(0, listItem);
                    for (int i = 1; i < arrayList.size(); i++) {
                        if(arrayList.get(i).getactivityChange_CODE() == 6) {
                            if (arrayList.get(0).getUserId().equals(arrayList.get(i).getUserId())) {
                                countMessage = arrayList.get(i).getCountMessage() + 1;
                                arrayList.remove(i);
                                arrayList.remove(0);
                                listItem.settxt("새로운 메세지 " + countMessage + "건이 있습니다.");
                                listItem.setCountMessage(countMessage);
                                arrayList.add(0, listItem);
                            }
                        }
                    }
                }
                else
                {
                    arrayList.add(0, listItem);
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
                alarmTxt = "Q&A 질문에 대한 답변이 완료되었습니다.";
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
                alarmTxt = "신고하기에 대한 조치가 완료되었습니다.";
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, "Talent Planet", alarmTxt, formatedDate, 5, R.drawable.icon_delete, false));
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                break;
            }
            case "Interest": {
                try {
                    JSONObject obj = new JSONObject(datas);
                    Log.d("long time = ", obj.getLong("CREATION_DATE") + "");
                    Date tempDate = new Date(obj.getLong("CREATION_DATE"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
                    sdf.setTimeZone(time);
                    unformatedDate = sdf.format(tempDate);
                    Log.d("unformatedDate", unformatedDate);
                    userName = obj.getString("USER_NAME");
                    talentID = obj.getInt("TALENT_ID");
                    talentType = (obj.getString("TALENT_FLAG").equals("Y"))? 2 : 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String formatedDate = dateFormat(unformatedDate);
                Log.d(formatedDate,"formatedDate");
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, userName, talentID, "Talent Planet", formatedDate, 1, talentType, R.drawable.icon_delete, false));
                SaveSharedPreference.setPrefAlarmArray(getApplicationContext(), arrayList);
                break;
            }
            case "InterestingMatching":{
                try {
                    JSONObject obj = new JSONObject(datas);
                    Date tempDate = new Date(obj.getLong("LAST_UPDATE_DATE"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
                    sdf.setTimeZone(time);
                    unformatedDate = sdf.format(tempDate);
                    userName = obj.getString("USER_NAME");
                    talentID = obj.getInt("TALENT_ID");
                    talentType = (obj.getString("TALENT_FLAG").equals("Y"))? 2 : 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String formatedDate = dateFormat(unformatedDate);
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, userName, talentID, "Talent Planet", formatedDate, 1, talentType, R.drawable.icon_delete, false));
                break;
            }
            case "InterestingCancel":{
                try {
                    JSONObject obj = new JSONObject(datas);
                    Date tempDate = new Date(obj.getLong("LAST_UPDATE_DATE"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
                    sdf.setTimeZone(time);
                    unformatedDate = sdf.format(tempDate);
                    userName = obj.getString("USER_NAME");
                    talentID = obj.getInt("TALENT_ID");
                    talentType = (obj.getString("TALENT_FLAG").equals("Y"))? 2 : 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String formatedDate = dateFormat(unformatedDate);
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, userName, talentID, "Talent Planet", formatedDate, 1, talentType, R.drawable.icon_delete, false));
                break;
            }
            case "InterestingComplete":{
                try {
                    JSONObject obj = new JSONObject(datas);
                    Date tempDate = new Date(obj.getLong("LAST_UPDATE_DATE"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
                    sdf.setTimeZone(time);
                    unformatedDate = sdf.format(tempDate);
                    userName = obj.getString("USER_NAME");
                    talentID = obj.getInt("TALENT_ID");
                    talentType = (obj.getString("TALENT_FLAG").equals("Y"))? 2 : 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String formatedDate = dateFormat(unformatedDate);
                arrayList.add(0, new ListItem(R.drawable.logo_fakefile, userName, talentID, "Talent Planet", formatedDate, 1, talentType, R.drawable.icon_delete, false));
                break;
            }
        }
    }

    private void addNotificationList(String type){
        intent1 = null;
        switch (type) {
            case "Message": {
                String userId = null;
                String recieverID1 = null;
                try {
                    JSONObject obj = new JSONObject(datas);
                    String userName = obj.getString("USER_NAME");
                    userId = obj.getString("USER_ID");
                    String unformatedDate = obj.getString("CREATION_DATE_STRING");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (messagePushGrant) {
                    if (topActivityName.equals("com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity")) {
                        return;
                    } else if (topActivityName.equals("com.example.accepted.acceptedtalentplanet.Messanger.Chatting.MainActivity") && userId.equals(receiverID)) {
                        return;
                    } else {
                        countAlarmPush_Message++;
                        alarmType = "Message";
                        alarmTxt = "새로운 메세지 " + countAlarmPush_Message + "건이 도착했습니다.";
                        intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity.class);
                        intent1.putExtra("alarmType", "Message");
                    }
                }
                break;
            }
            case "QNA":
                if (answerPushGrant) {
                    countAlarmPush_Qna++;
                    alarmType = "QNA";
                    alarmTxt = "문의하신 Q&A " + countAlarmPush_Qna + "건이 답변 완료되었습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList.MainActivity.class);
                    intent1.putExtra("alarmType", "QNA");
                }
                break;
            case "Claim":
                if (answerPushGrant) {
                    countAlarmPush_Claim++;
                    alarmType = "Claim";
                    alarmTxt = "신고하기 " + countAlarmPush_Claim + "건이 조치 완료되었습니다.";
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList.MainActivity.class);
                    intent1.putExtra("alarmType", "Claim");
                }
                break;
            case "Interest": {
                if(conditionPushGrant) {
                    int talentType = -1;
                    try {
                        JSONObject obj = new JSONObject(datas);
                        talentType = (obj.getString("TALENT_FLAG").equals("Y")) ? 2 : 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (talentType == 1) {
                        countAlarmPush_Intersting_Give++;
                        alarmTxt = "재능 드림에 받은 관심이 " + countAlarmPush_Intersting_Give + "건 있습니다.";
                        intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                        intent1.putExtra("TalentFlag", "Give");
                    } else {
                        countAlarmPush_Intersting_Take++;
                        alarmTxt = "관심 재능에 받은 관심이 " + countAlarmPush_Intersting_Take + "건 있습니다.";
                        intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                        intent1.putExtra("TalentFlag", "Take");
                    }
                }
                break;
            }
            case "InterestingMatching": {
                if(conditionPushGrant) {
                    int talentType = -1;
                    try {
                        JSONObject obj = new JSONObject(datas);
                        talentType = (obj.getString("TALENT_FLAG").equals("Y")) ? 2 : 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                    if (talentType == 1) {
                        countAlarmPush_Condition++;
                        alarmTxt = "재능 드림 상태가 진행중으로 변경되었습니다.";
                        intent1.putExtra("TalentCondition_TalentFlag", "Give");
                    } else {
                        countAlarmPush_Condition++;
                        alarmTxt = "관심 재능 상태가 진행중으로 변경되었습니다.";
                        intent1.putExtra("TalentCondition_TalentFlag", "Take");
                    }
                    break;
                }
            }

            case "InterestingCancel":
                {
                    if(conditionPushGrant) {
                        int talentType = -1;
                        try {
                            JSONObject obj = new JSONObject(datas);
                            talentType = (obj.getString("TALENT_FLAG").equals("Y")) ? 2 : 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (talentType == 1) {
                            countAlarmPush_Condition++;
                            alarmTxt = "진행 중인 재능 드림이 취소 되었습니다.";
                        } else {
                            countAlarmPush_Condition++;
                            alarmTxt = "진행 중인 관심 재능이 취소되었습니다.";
                        }
                        alarmType = "InterestingCancel";
                        intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.SharingList.MainActivity.class);
                        intent1.putExtra("alarmType", alarmType);
                        break;
                    }
            }
                case "InterestingComplete": {
                    if (conditionPushGrant) {
                        int talentType = -1;
                        try {
                            JSONObject obj = new JSONObject(datas);
                            talentType = (obj.getString("TALENT_FLAG").equals("Y")) ? 2 : 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent1 = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                        if (talentType == 1) {
                            countAlarmPush_Condition++;
                            alarmTxt = "진행 중인 재능 드림이 완료되었습니다.";
                            intent1.putExtra("TalentCondition_TalentFlag", "Give");

                        } else {
                            countAlarmPush_Condition++;
                            alarmTxt = "진행 중인 관심 재능이완료 되었습니다.";
                            intent1.putExtra("TalentCondition_TalentFlag", "Take");
                        }
                        break;
                    }
                }
        }
    }

    private void getMessage(String datas){
        try {
            JSONObject obj = new JSONObject(datas);
            String dbName = "/accepted.db";
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);

            Log.d("TAG", "Datas: " + datas.toString());




            int roomID = SaveSharedPreference.makeChatRoom(getApplicationContext(), obj.getString("USER_ID"), obj.getString("USER_NAME"));
            sqLiteDatabase.execSQL("INSERT OR REPLACE INTO TB_CHAT_LOG(MESSAGE_ID, ROOM_ID, USER_ID, CONTENT, CREATION_DATE, READED_FLAG) VALUES (" + obj.getString("MESSAGE_ID") + ", " + roomID + ", '" + obj.getString("USER_ID") + "','" + obj.getString("CONTENT").replace("'", "''") + "','" + obj.getString("CREATION_DATE_STRING") + "', 'N')");
            sqLiteDatabase.close();
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
        sdf.setTimeZone(time);
        String nowDateStr = sdf.format(tempDate);
        String[] nowDateTemp = nowDateStr.split(",");
        String nowDate = nowDateTemp[0];

        Log.d("LastDate, NowDate", lastDate + ", " + nowDateStr);

        String[] dateTemp = lastDate.split(",");
        lastDate = dateTemp[0];
        String dateTime = dateTemp[1].substring(0, 8);
        lastDate = (lastDate.equals(nowDate)) ? dateTime : lastDate;

        return lastDate;
    }

    private static MessageReceivedListener mMessageReceivedListener;
    public interface MessageReceivedListener{
        public void onMessageRecieved();
    }

    public static void setOnMessageReceivedListener(MessageReceivedListener listener){
        mMessageReceivedListener = listener;
    }

    private void update(){
        if(mMessageReceivedListener != null){
            mMessageReceivedListener.onMessageRecieved();
        }
    }


}
