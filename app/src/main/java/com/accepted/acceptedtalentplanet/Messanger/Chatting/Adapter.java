package com.accepted.acceptedtalentplanet.Messanger.Chatting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-03-05.
 */

public class Adapter extends BaseAdapter {

    private ArrayList<ListItem> messanger_Chatting_Arraylist = new ArrayList<>();
    Context mContext;
    String filePath;
    String receiverID;


    public Adapter(ArrayList<ListItem> messanger_Chatting_Arraylist, Context mContext, String filePath, String receiverID) {
        this.messanger_Chatting_Arraylist = messanger_Chatting_Arraylist;
        this.mContext = mContext;
        this.filePath = filePath;
        this.receiverID = receiverID;
    }

    public Adapter()
    {}

    @Override

    public int getCount() {
        return messanger_Chatting_Arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return messanger_Chatting_Arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        view = null;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.messanger_chatting_bg, parent, false);
            holder = new ViewHolder();

        holder.Messanger_Chatting_Picture = view.findViewById(R.id.Messanger_Chatting_Picture);
        holder.Messanger_Chatting_Txt = view.findViewById(R.id.Messanger_Chatting_Txt);
        holder.Messanger_Chatting_Date = view.findViewById(R.id.Messanger_Chatting_Date);
        holder.Messanger_Chatting_DateLine = view.findViewById(R.id.Messanger_Chatting_DateLine);
        holder.Messanger_Chatting_Date_String = view.findViewById(R.id.Messanger_Chatting_Date_String);

        view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

            holder.Messanger_Chatting_Txt.setText(messanger_Chatting_Arraylist.get(position).getMessage());
            holder.Messanger_Chatting_Date.setText(messanger_Chatting_Arraylist.get(position).getDate());

        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int size = Math.round(5*dm.density);

        RelativeLayout.LayoutParams Messanger_ChattingTxt = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams Messanger_ChattingDate = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Messanger_ChattingTxt.setMargins(size,0,size,0);
        holder.Messanger_Chatting_Txt.setLayoutParams(Messanger_ChattingTxt);
        int maxWidth = (int) (dm.widthPixels*0.7);
        holder.Messanger_Chatting_Txt.setMaxWidth(maxWidth);
        holder.Messanger_Chatting_Date.setLayoutParams(Messanger_ChattingDate);
        Messanger_ChattingDate.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.Messanger_Chatting_Txt);

        holder.Messanger_Chatting_Txt.setText(messanger_Chatting_Arraylist.get(position).getMessage());

        if(messanger_Chatting_Arraylist.get(position).getMessageType()==2)
        {
            if(!filePath.equals("NODATA")){
                Glide.with(mContext).load(SaveSharedPreference.getImageUri() + filePath).into(holder.Messanger_Chatting_Picture);
            }else{
                holder.Messanger_Chatting_Picture.setBackgroundResource(messanger_Chatting_Arraylist.get(position).getPicture());
            }
            holder.Messanger_Chatting_Txt.setBackgroundResource(R.drawable.bgr_messanger_chatting_get);
            Messanger_ChattingTxt.addRule(RelativeLayout.RIGHT_OF, R.id.Messanger_Chatting_Picture);
            Messanger_ChattingDate.addRule(RelativeLayout.RIGHT_OF, R.id.Messanger_Chatting_Txt);
        }else{
            holder.Messanger_Chatting_Picture.setVisibility(View.GONE);
            holder.Messanger_Chatting_Txt.setBackgroundResource(R.drawable.bgr_messanger_chatting_send);
            Messanger_ChattingTxt.addRule( RelativeLayout.ALIGN_PARENT_RIGHT);
            Messanger_ChattingDate.addRule(RelativeLayout.LEFT_OF, R.id.Messanger_Chatting_Txt);
        }

        if(messanger_Chatting_Arraylist.get(position).isPicture())
        {
            holder.Messanger_Chatting_Picture.setVisibility(View.VISIBLE);
            final View finalView = view;
            holder.Messanger_Chatting_Picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.myDialog));

                            getTalentID(receiverID, finalView);

                        }
                    });
            }else
        {
            holder.Messanger_Chatting_Picture.setVisibility(View.INVISIBLE);
            holder.Messanger_Chatting_Picture.setOnClickListener(null);
        }

        if(messanger_Chatting_Arraylist.get(position).isTimeChanged())
        {
            String dateStr = messanger_Chatting_Arraylist.get(position).getDate();
            String[] dateStr2 = dateStr.split(",");
            dateStr = dateStr2[1].substring(0, 8);
            holder.Messanger_Chatting_Date.setVisibility(View.VISIBLE);
            holder.Messanger_Chatting_Date.setText(dateStr);
        }else
        {
            holder.Messanger_Chatting_Date.setVisibility(View.INVISIBLE);
        }

        if(messanger_Chatting_Arraylist.get(position).isDateChanged())
        {
            String dateStr = messanger_Chatting_Arraylist.get(position).getDate();
            String[] dateStr2 = dateStr.split(",");
            dateStr = dateStr2[0];
            holder.Messanger_Chatting_DateLine.setVisibility(View.VISIBLE);
            holder.Messanger_Chatting_Date_String.setText(dateStr);
        }else
        {
            holder.Messanger_Chatting_DateLine.setVisibility(View.GONE);
        }

        return view;
    }

    static class ViewHolder
    {
        ImageView Messanger_Chatting_Picture;
        LinearLayout Messanger_Chatting_DateLine;
        TextView Messanger_Chatting_Txt;
        TextView Messanger_Chatting_Date;
        TextView Messanger_Chatting_Date_String;
    }

    public void refreshAdapter(ArrayList<ListItem> items){
        this.messanger_Chatting_Arraylist.clear();
        this.messanger_Chatting_Arraylist.addAll(items);
        notifyDataSetChanged();
    }

    public void getTalentID(final String userID, final View finalView) {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Chat/getTalentID.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    final int giveTalentID = obj.getInt("GIVE_TALENT_ID");
                    final int takeTalentID = obj.getInt("TAKE_TALENT_ID");

                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.myDialog));

                    AlarmDeleteDialog.setMessage("상대방 프로필 보기")
                            .setPositiveButton("관심 재능", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(takeTalentID == -1){
                                        Toast.makeText(mContext,"상대방의 관심 재능이 등록되지 않았습니다.",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent intent = new Intent(mContext, com.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("TalentID", String.valueOf(takeTalentID));
                                        intent.putExtra("TalentFlag", "Take");
                                        mContext.startActivity(intent);
                                    }
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("재능 드림", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(giveTalentID == -1){
                                        Toast.makeText(mContext,"상대방의 재능 드림이 등록되지 않았습니다.",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("TalentID", String.valueOf(giveTalentID));
                                        intent.putExtra("TalentFlag", "Give");
                                        mContext.startActivity(intent);
                                    }
                                    dialog.cancel();
                                }
                            })
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = AlarmDeleteDialog.create();
                    alertDialog.show();
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(finalView.getResources().getColor(R.color.loginPasswordLost));
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(finalView.getResources().getColor(R.color.loginPasswordLost));
                    alertDialog.getButton((DialogInterface.BUTTON_NEUTRAL)).setTextColor(finalView.getResources().getColor(R.color.loginPasswordLost));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }
}
