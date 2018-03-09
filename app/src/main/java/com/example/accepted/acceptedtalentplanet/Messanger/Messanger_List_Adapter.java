package com.example.accepted.acceptedtalentplanet.Messanger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-03-05.
 */

public class Messanger_List_Adapter extends BaseAdapter {

    private ArrayList<Messanger_List_Item> messanger_Arraylist = new ArrayList<>();
    Context mContext;


    public Messanger_List_Adapter(ArrayList<Messanger_List_Item> messanger_Arraylist, Context mContext) {
        this.messanger_Arraylist = messanger_Arraylist;
        this.mContext = mContext;
    }

    public Messanger_List_Adapter()
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
            for (Messanger_List_Item item : messanger_Arraylist) {
                item.setMessanger_DeleteBtn(true);

            }

        } else {
            for (Messanger_List_Item item : messanger_Arraylist) {
                item.setMessanger_DeleteBtn(false);
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int messanger_ListView_height = (int) (metrics.heightPixels*0.1);

        ViewGroup.LayoutParams params1 = view.getLayoutParams();
        params1.height = messanger_ListView_height;
        view.setLayoutParams(params1);


        holder.messsanger_Pic = view.findViewById(R.id.Messanger_List_Picture);
        holder.messanger_Name = view.findViewById(R.id.Messanger_List_Name);
        holder.messanger_Content = view.findViewById(R.id.Messanger_List_Txt);
        holder.messanger_Count = view.findViewById(R.id.Messanger_List_Count);
        holder.Messanger_List_LL = view.findViewById(R.id.Messanger_List_LL);
        holder.messanger_Date = view.findViewById(R.id.Messanger_List_DateList);
        holder.Messanger_List_DateLL = view.findViewById(R.id.Messanger_List_DateLL);
        holder.Messanger_List_DeleteList = view.findViewById(R.id.Messanger_List_DeleteList);


        view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Messanger_Chatting.class);
                i.putExtra("roomID", roomID);
                i.putExtra("userID", userID);
                i.putExtra("userName", messanger_Arraylist.get(position).getMessanger_Name());
                mContext.startActivity(i);
            }
        });




        holder.Messanger_List_DeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(mContext);
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
    }
}
