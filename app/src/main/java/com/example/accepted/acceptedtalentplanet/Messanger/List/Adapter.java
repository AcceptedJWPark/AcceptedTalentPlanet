package com.example.accepted.acceptedtalentplanet.Messanger.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-03-05.
 */

public class Adapter extends BaseAdapter {

    private ArrayList<ListItem> messanger_Arraylist = new ArrayList<>();
    Context mContext;


    public Adapter(ArrayList<ListItem> messanger_Arraylist, Context mContext) {
        this.messanger_Arraylist = messanger_Arraylist;
        this.mContext = mContext;
    }

    public Adapter()
    {}

    @Override

    public int getCount() {
        return messanger_Arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return messanger_Arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void switchFlag_deleteBtn(boolean deleteBtn_Clicked) {
        if (deleteBtn_Clicked) {
            for (ListItem item : messanger_Arraylist) {
                item.setMessanger_DeleteBtn(true);

            }

        } else {
            for (ListItem item : messanger_Arraylist) {
                item.setMessanger_DeleteBtn(false);
            }
        }
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final int roomID = messanger_Arraylist.get(position).getRoomID();
        final String userID = messanger_Arraylist.get(position).getMessanger_userID();
        View view = convertView;
        ViewHolder holder;
        view = null;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.messanger_list_bg, parent, false);
            holder = new ViewHolder();

            holder.messsanger_Pic = view.findViewById(R.id.iv_pictureContainer_List_Messanger);
            holder.messanger_Name = view.findViewById(R.id.ll_name_List_Messanger);
            holder.messanger_Content = view.findViewById(R.id.ll_txt_List_Messanger);
            holder.messanger_Count = view.findViewById(R.id.tv_count_List_Alarm);
            holder.Messanger_List_LL = view.findViewById(R.id.ll_Messanger_List);
            holder.messanger_Date = view.findViewById(R.id.tv_date_Alarm);
            holder.Messanger_List_DateLL = view.findViewById(R.id.ll_deleteContainer_Alarm);
            holder.Messanger_List_DeleteList = view.findViewById(R.id.iv_delete_List_Messanger);

            holder.ll_pictureContainer = view.findViewById(R.id.ll_pictureContainer_List_Messanger);
            holder.ll_txtContainer = view.findViewById(R.id.ll_txtContainer_List_Messanger);
            holder.rl_dateContainer = view.findViewById(R.id.rl_dateContainer_List_Messanger);

            holder.trashView1 = view.findViewById(R.id.trashView1_MessageList);
            holder.trashView2 = view.findViewById(R.id.trashView2_MessageList);
            holder.trashView3 = view.findViewById(R.id.trashView3_MessageList);
            holder.trashView4 = view.findViewById(R.id.trashView4_MessageList);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int messanger_ListView_height = (int) (metrics.heightPixels*0.1);
            int Interesting_ListView_width = metrics.widthPixels;

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            ViewGroup.LayoutParams params2 = holder.ll_pictureContainer.getLayoutParams();
            ViewGroup.LayoutParams params3 = holder.ll_txtContainer.getLayoutParams();
            ViewGroup.LayoutParams params4 = holder.rl_dateContainer.getLayoutParams();
            ViewGroup.LayoutParams params5 = holder.trashView1.getLayoutParams();
            ViewGroup.LayoutParams params6 = holder.trashView2.getLayoutParams();
            ViewGroup.LayoutParams params7 = holder.trashView3.getLayoutParams();
            ViewGroup.LayoutParams params8 = holder.trashView4.getLayoutParams();

            params1.height = messanger_ListView_height;
            params2.width = (int) (Interesting_ListView_width * 0.13);
            params2.height = (int) (Interesting_ListView_width * 0.13);
            params3.width = (int) (Interesting_ListView_width * 0.57);
            params4.width = (int) (Interesting_ListView_width * 0.16);
            params5.width = (int) (Interesting_ListView_width * 0.04);
            params6.width = (int) (Interesting_ListView_width * 0.04);
            params7.width = (int) (Interesting_ListView_width * 0.04);
            params8.width = (int) (Interesting_ListView_width * 0.04);

            view.setLayoutParams(params1);
            holder.ll_pictureContainer.setLayoutParams(params2);
            holder.ll_txtContainer.setLayoutParams(params3);
            holder.rl_dateContainer.setLayoutParams(params4);
            holder.trashView1.setLayoutParams(params5);
            holder.trashView2.setLayoutParams(params6);
            holder.trashView3.setLayoutParams(params7);
            holder.trashView4.setLayoutParams(params8);





        view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.Messanger.Chatting.MainActivity.class);
                i.putExtra("roomID", roomID);
                i.putExtra("userID", userID);
                i.putExtra("userName", messanger_Arraylist.get(position).getMessanger_Name());
                mContext.startActivity(i);
            }
        });


        final View finalView = view;
        holder.ll_pictureContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.myDialog));

                AlarmDeleteDialog.setMessage("상대방 프로필 보기")
                        .setPositiveButton("관심 재능", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"관심 재능 클릭",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("재능 드림", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"재능 드림 클릭",Toast.LENGTH_SHORT).show();
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

            }
        });


        holder.Messanger_List_DeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.myDialog));
                float textSize = mContext.getResources().getDimension(R.dimen.DialogTxtSize);
                AlarmDeleteDialog.setMessage("메시지를 삭제 하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SQLiteDatabase sqliteDatabase;
                                String dbName = "/accepted.db";
                                try {
                                    sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(mContext.getFilesDir() + dbName, null);
                                    String updateSql = "UPDATE TB_CHAT_LOG SET READED_FLAG = 'Y' WHERE ROOM_ID = " + messanger_Arraylist.get(position).getRoomID();
                                    sqliteDatabase.execSQL(updateSql);

                                    Log.d("update1", updateSql);

                                    String selectMaxMessageID = "SELECT MAX(MESSAGE_ID) FROM TB_CHAT_LOG WHERE ROOM_ID = "+ messanger_Arraylist.get(position).getRoomID();
                                    Cursor cursor = sqliteDatabase.rawQuery(selectMaxMessageID, null);
                                    cursor.moveToFirst();


                                    Log.d("select", selectMaxMessageID);

                                    String updateSql2 = "UPDATE TB_CHAT_ROOM SET ACTIVATE_FLAG = 'N', START_MESSAGE_ID = "+ cursor.getInt(0) + " WHERE ROOM_ID = "+ messanger_Arraylist.get(position).getRoomID();
                                    sqliteDatabase.execSQL(updateSql2);

                                    sqliteDatabase.close();

                                    Log.d("update2", updateSql2);
                                } catch (SQLiteException e) {
                                    e.printStackTrace();
                                }

                                messanger_Arraylist.remove(position);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = AlarmDeleteDialog.create();
                alertDialog.show();
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(finalView.getResources().getColor(R.color.loginPasswordLost));
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(finalView.getResources().getColor(R.color.loginPasswordLost));
                TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                msgView.setTextSize(textSize);
            }
        });

        if(messanger_Arraylist.get(position).isMessanger_DeleteBtn())
        {
            holder.Messanger_List_DateLL.setVisibility(View.GONE);
            holder.Messanger_List_DeleteList.setVisibility(View.VISIBLE);
        }else
        {
            holder.Messanger_List_DateLL.setVisibility(View.VISIBLE);
            holder.Messanger_List_DeleteList.setVisibility(View.GONE);
        }
            if(messanger_Arraylist.get(position).getPicture() == null) {
                holder.messsanger_Pic.setBackgroundResource(messanger_Arraylist.get(position).getMesssanger_Pic());
            }
            else
            {
                holder.messsanger_Pic.setBackground(new BitmapDrawable(messanger_Arraylist.get(position).getPicture()));
            }
            holder.messanger_Name.setText(messanger_Arraylist.get(position).getMessanger_Name());
            holder.messanger_Content.setText(messanger_Arraylist.get(position).getMessanger_Content());
            holder.messanger_Date.setText(messanger_Arraylist.get(position).getMessanger_Date());
            holder.messanger_Count.setText(String.valueOf(messanger_Arraylist.get(position).getMessanger_Count()));

            if(messanger_Arraylist.get(position).getMessanger_Count() == 0)
            {
                holder.messanger_Count.setVisibility(View.INVISIBLE);
                holder.Messanger_List_LL.setBackgroundColor(0);
        }else
        {
            int messanger_Unread_Color = view.getResources().getColor(R.color.messangerList);
            holder.Messanger_List_LL.setBackgroundColor(messanger_Unread_Color);
        }

        return view;
    }

    static class ViewHolder
    {
        ImageView messsanger_Pic;
        TextView messanger_Name;
        TextView messanger_Content;
        TextView messanger_Date;
        TextView messanger_Count;
        LinearLayout Messanger_List_DateLL;
        LinearLayout Messanger_List_LL;
        ImageView Messanger_List_DeleteList;

        LinearLayout ll_pictureContainer;
        LinearLayout ll_txtContainer;
        RelativeLayout rl_dateContainer;

        View trashView1;
        View trashView2;
        View trashView3;
        View trashView4;
    }
}
