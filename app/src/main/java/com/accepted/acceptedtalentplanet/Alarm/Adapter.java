package com.accepted.acceptedtalentplanet.Alarm;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Adapter extends BaseAdapter {

    private ArrayList<ListItem> arrayList = new ArrayList<ListItem>();
    private Context mContext;

    public Adapter(Context context, ArrayList<ListItem> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<ListItem> getArrayList()
    {
        return arrayList;
    }

    public void switchingFlag(boolean deletedFlag){
        if(deletedFlag){
            for(ListItem item : arrayList){
                item.setdeleteClicked(true);
            }
        }else{
            for(ListItem item : arrayList){
                item.setdeleteClicked(false);
            }
        }
    }

    public void initFlag(){
        for(ListItem item :arrayList){
            item.setdeleteClicked(false);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {


        View view = convertView;
        ViewHolder holder;
        view = null;



        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alarm_listviewbg, viewGroup,false);
            holder = new ViewHolder();

            holder.iv_Picture = view.findViewById(R.id.iv_Picture_Alarm);
            holder.tv_Name = view.findViewById(R.id.tv_Name_Alarm);
            holder.tv_Txt = view.findViewById(R.id.tv_Txt_Alarm);
            holder.tv_Date = view.findViewById(R.id.tv_date_Alarm);
            holder.iv_DeleteIcon = view.findViewById(R.id.iv_DeleteIcon_Alarm);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);

            view.setTag(holder);
        }else
        {
            holder=(ViewHolder) view.getTag();
        }

        holder.iv_Picture.setBackgroundResource(arrayList.get(position).getpicture());
        holder.tv_Name.setText(arrayList.get(position).getName());
        holder.tv_Txt.setText(arrayList.get(position).gettxt());

        if (arrayList.get(position).getdeleteClicked())
        {
            holder.iv_DeleteIcon.setVisibility(View.VISIBLE);
        }else
        {
            holder.iv_DeleteIcon.setVisibility(View.GONE);
        }


        holder.iv_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(mContext);
        float textSize = mContext.getResources().getDimension(R.dimen.DialogTxtSize);
        AlarmDeleteDialog.setMessage("알람을 삭제 하시겠습니까?")
        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(position);
                notifyDataSetChanged();
                SaveSharedPreference.setPrefAlarmArray(mContext, arrayList);
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


        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int size = Math.round(5*dm.density);

        String lastDate = arrayList.get(position).getDate();
        String alarmTxt = arrayList.get(position).gettxt();
        Log.d(lastDate,"lastDate");

        switch (arrayList.get(position).getactivityChange_CODE()) {
            case 1:
                    if(arrayList.get(position).getalarmType_CODE() == 1)
                    {
                    holder.tv_Txt.setText("회원님의 재능드림에 관심을 보냈습니다.");
                    holder.tv_Date.setText(lastDate);
                    }
                    else
                    {
                    holder.tv_Txt.setText("회원님의 관심재능에 관심을 보냈습니다.");
                    holder.tv_Date.setText(lastDate);
                    }
                    break;
            case 2:

                    if(arrayList.get(position).getalarmType_CODE() == 1)
                    {
                        holder.tv_Txt.setText("재능 드림 상태가 진행중으로 변경되었습니다.");
                        holder.tv_Date.setText(lastDate);
                    }
                    else if(arrayList.get(position).getalarmType_CODE() == 2)
                    {
                        holder.tv_Txt.setText("관심 재능 상태가 진행중으로 변경되었습니다.");
                        holder.tv_Date.setText(lastDate);
                    }
                    else if(arrayList.get(position).getalarmType_CODE() == 3)
                    {
                        holder.tv_Txt.setText("진행 중인 재능 드림이 완료되었습니다.");
                        holder.tv_Date.setText(lastDate);
                    }
                    else
                    {
                        holder.tv_Txt.setText("진행 중인 관심 재능이 완료되었습니다.");
                        holder.tv_Date.setText(lastDate);
                    }
                break;

            case 3:

                if(arrayList.get(position).getalarmType_CODE() == 1)
                {
                    holder.tv_Txt.setText("님이 회원님의 관심을 취소하였습니다.");
                    holder.tv_Date.setText(lastDate);
                }
                else if(arrayList.get(position).getalarmType_CODE() == 2)
                {
                    holder.tv_Txt.setText("진행 중인 재능 드림이 취소 되었습니다.");
                    holder.tv_Date.setText(lastDate);
                }
                else
                {
                    holder.tv_Txt.setText("진행 중인 관심 재능이 취소 되었습니다.");
                    holder.tv_Date.setText(lastDate);
                }

                break;
            case 4:
            {
                holder.tv_Txt.setText(alarmTxt);
                holder.tv_Date.setText(lastDate);
                break;
            }

            case 5 :
            {
                holder.tv_Txt.setText(alarmTxt);
                holder.tv_Date.setText(lastDate);
                break;
            }
            case 6:
            {
                holder.tv_Date.setText(lastDate);
                break;
            }
            default:break;
        }




        return view;
    }

    static class ViewHolder
    {
        ImageView iv_Picture;
        TextView tv_Name;
        TextView tv_Txt;
        TextView tv_Date;
        ImageView iv_DeleteIcon;

    }

}

