package com.example.accepted.acceptedtalentplanet.Alarm;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

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

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            holder.iv_Picture = view.findViewById(R.id.iv_Picture_Alarm);
            holder.tv_Name = view.findViewById(R.id.tv_Name_Alarm);
            holder.tv_Txt = view.findViewById(R.id.tv_Txt_Alarm);
            holder.tv_Date = view.findViewById(R.id.tv_date_Alarm);
            holder.iv_DeleteIcon = view.findViewById(R.id.iv_DeleteIcon_Alarm);
            holder.ll_deleteContainer = view.findViewById(R.id.ll_deleteContainer_Alarm);
            holder.ll_txtContainer = view.findViewById(R.id.ll_txtContainer_Alarm);
            holder.ll_picContainer = view.findViewById(R.id.ll_picContainer_Alarm);

            view.setTag(holder);
        }else
        {
            holder=(ViewHolder) view.getTag();
        }

        holder.iv_Picture.setBackgroundResource(arrayList.get(position).getpicture());
        holder.tv_Name.setText(arrayList.get(position).getName());
        holder.tv_Date.setText(arrayList.get(position).getDate());

        if (arrayList.get(position).getdeleteClicked())
        {
            holder.ll_deleteContainer.setVisibility(View.GONE);
            holder.iv_DeleteIcon.setVisibility(View.VISIBLE);
        }else
        {
            holder.ll_deleteContainer.setVisibility(View.VISIBLE);
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

        switch (arrayList.get(position).getactivityChange_CODE()) {
            case 1:
                switch (arrayList.get(position).getalarmType_CODE())
                {
                    case 1:
                        {
                            holder.tv_Txt.setText("님이 회원님의 재능드림에 관심을 보냈습니다.");
                        break;
                        }
                    case 2:
                        {
                            holder.tv_Txt.setText("님이 회원님의 관심재능에 관심을 보냈습니다.");
                        break;
                        }
                }
                break;
            case 2:
                switch (arrayList.get(position).getalarmType_CODE())
                {
                    case 1:
                        {
                            holder.tv_Txt.setText("님과 재능드림이 진행 중입니다.");
                        break;
                        }
                    case 2:
                        {
                            holder.tv_Txt.setText("님과 관심재능이 진행 중입니다.");
                        break;
                        }
                    case 3:
                        {
                            holder.tv_Txt.setText("님과 재능드림이 완료되었습니다.");
                        break;
                        }
                    case 4:
                        {
                            holder.tv_Txt.setText("님과 관심재능이 완료되었습니다.");
                        break;
                        }
                }
                break;
            case 3:
                switch (arrayList.get(position).getalarmType_CODE()) {
                    case 1: {
                        holder.tv_Txt.setText("님이 회원님의 관심을 취소하였습니다.");
                        break;
                    }
                    case 2: {
                        holder.tv_Txt.setText("님이 재능드림 진행을 취소하였습니다.");
                        break;
                    }
                    case 3: {
                        holder.tv_Txt.setText("님이 관심재능 진행을 취소하였습니다.");
                        break;
                    }
                }
                break;
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
        LinearLayout ll_deleteContainer;
        LinearLayout ll_txtContainer;
        LinearLayout ll_picContainer;
    }

}

