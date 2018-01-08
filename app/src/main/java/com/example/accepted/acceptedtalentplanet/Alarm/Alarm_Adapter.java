package com.example.accepted.acceptedtalentplanet.Alarm;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
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

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Alarm_Adapter extends BaseAdapter {

    private ArrayList<Alarm_ListItem> list_ArrayList = new ArrayList<Alarm_ListItem>();
    Context mContext;

    public Alarm_Adapter(Context context, ArrayList<Alarm_ListItem> list_ArrayList) {
        this.mContext = context;
        this.list_ArrayList = list_ArrayList;
    }

    public int getViewTypeCount()
    {
        return getCount();
    }

    public int getItemViewType(int position)
    {
        return position;
    }


    @Override
    public int getCount() {
        return list_ArrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return list_ArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

            holder.Alarm_Picture = view.findViewById(R.id.Alarm_Picture);
            holder.Alarm_Name = view.findViewById(R.id.Alarm_Name);
            holder.Alarm_Txt = view.findViewById(R.id.Alarm_Txt);
            holder.Alarm_RegistDate1 = view.findViewById(R.id.Alarm_RegistDate1);
            holder.Alarm_RegistDate2 = view.findViewById(R.id.Alarm_RegistDate2);
            holder.Alarm_DeleteList = view.findViewById(R.id.Alarm_DeleteList);
            holder.alarm_TxtLL = view.findViewById(R.id.alarm_TxtLL);
            holder.pictureContainerLL = view.findViewById(R.id.pictureContainerLL);

            view.setTag(holder);
        }else
        {
            holder=(ViewHolder) view.getTag();
        }

        holder.Alarm_Picture.setImageResource(list_ArrayList.get(position).getpicture());
        holder.Alarm_Name.setText(list_ArrayList.get(position).getName());
        holder.Alarm_RegistDate1.setText(list_ArrayList.get(position).getregistDate1());
        holder.Alarm_DeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(mContext);
        float textSize = mContext.getResources().getDimension(R.dimen.DialogTxtSize);
        AlarmDeleteDialog.setMessage("알람을 삭제 하시겠습니까?")
        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list_ArrayList.remove(position);
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


        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int size = Math.round(5*dm.density);

        switch (list_ArrayList.get(position).getactivityChange_CODE()) {
            case 1:
                switch (list_ArrayList.get(position).getalarmType_CODE())
                {
                    case 1:
                        {
                            holder.Alarm_Txt.setText("님이 회원님의 재능드림에 관심을 보냈습니다.");
                            holder.Alarm_RegistDate2.setText("관심 등록");
                        break;
                        }
                    case 2:
                        {
                            holder.Alarm_Txt.setText("님이 회원님의 관심재능에 관심을 보냈습니다.");
                            holder.Alarm_RegistDate2.setText("관심 등록");
                        break;
                        }
                }
                break;
            case 2:
                switch (list_ArrayList.get(position).getalarmType_CODE())
                {
                    case 1:
                        {
                            holder.Alarm_Txt.setText("님과 재능드림이 진행 중입니다.");
                            holder.Alarm_RegistDate2.setText("진행");
                        break;
                        }
                    case 2:
                        {
                            holder.Alarm_Txt.setText("님과 관심재능이 진행 중입니다.");
                            holder.Alarm_RegistDate2.setText("진행");
                        break;
                        }
                    case 3:
                        {
                            holder.Alarm_Txt.setText("님과 재능드림이 완료되었습니다.");
                            holder.Alarm_RegistDate2.setText("완료");
                        break;
                        }
                    case 4:
                        {
                            holder.Alarm_Txt.setText("님과 관심재능이 완료되었습니다.");
                            holder.Alarm_RegistDate2.setText("완료");
                        break;
                        }
                }
                break;
            case 3:
                switch (list_ArrayList.get(position).getalarmType_CODE()) {
                    case 1: {
                        holder.Alarm_Txt.setText("님이 회원님의 관심을 취소하였습니다.");
                        holder.Alarm_RegistDate2.setText("관심 취소");
                        break;
                    }
                    case 2: {
                        holder.Alarm_Txt.setText("님이 재능드림 진행을 취소하였습니다.");
                        holder.Alarm_RegistDate2.setText("진행 취소");
                        break;
                    }
                    case 3: {
                        holder.Alarm_Txt.setText("님이 관심재능 진행을 취소하였습니다.");
                        holder.Alarm_RegistDate2.setText("진행 취소");
                        break;
                    }
                }
                break;
            case 4:
                    {
                        holder.Alarm_Picture.setVisibility(View.GONE);
                        holder.pictureContainerLL.setVisibility(View.GONE);
                        holder.alarm_TxtLL.setPadding(size,0,0,0);
                        holder.alarm_TxtLL.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,6));
                        holder.Alarm_Txt.setText("1:1 문의하기 답변이 완료되었습니다.");
                        holder.Alarm_RegistDate2.setText("답변 완료");
                        break;
                    }
            case 5:
            {
                        holder.Alarm_Picture.setVisibility(View.GONE);
                        holder.pictureContainerLL.setVisibility(View.GONE);
                        holder.alarm_TxtLL.setPadding(size,0,0,0);
                        holder.alarm_TxtLL.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,6));
                        holder.Alarm_Txt.setText("신고하기 답변이 완료되었습니다.");
                        holder.Alarm_RegistDate2.setText("답변 완료");
                        break;
                }
            case 6:
                        {
                        holder.Alarm_Txt.setText("님이 메세지를 보냈습니다.");
                        holder.Alarm_RegistDate2.setText("보냄");
                        break;
                    }
        }


        return view;
    }

    static class ViewHolder
    {
        ImageView Alarm_Picture;
        TextView Alarm_Name;
        TextView Alarm_Txt;
        TextView Alarm_RegistDate1;
        TextView Alarm_RegistDate2;
        ImageView Alarm_DeleteList;
        LinearLayout alarm_TxtLL;
        LinearLayout pictureContainerLL;
    }

}

