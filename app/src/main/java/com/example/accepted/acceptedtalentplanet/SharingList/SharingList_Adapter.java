package com.example.accepted.acceptedtalentplanet.SharingList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Adapter;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_ClaimActivity;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListItem;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Accepted on 2017-09-29.
 */

public class SharingList_Adapter extends BaseAdapter{

       Context context;
    ArrayList<SharingList_Item> list_ArrayList;
    MySpinnerAdapter adapter = null;
    ArrayList<MyModel> models;

    public SharingList_Adapter(Context context, ArrayList<SharingList_Item> list_ArrayList) {
            this.context = context;
            this.list_ArrayList = list_ArrayList;
        models = new ArrayList<>();
        models.add(new MyModel("보기"));
        models.add(new MyModel("Profile 보기"));
        models.add(new MyModel("내역 삭제"));
        models.add(new MyModel("신고 하기"));
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
        final ViewHolder holder;
        view = null;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sharinglist_listviewbg, viewGroup, false);
            adapter = new MySpinnerAdapter(context, models);

            holder = new ViewHolder();

            holder.SharingList_ConditionType = view.findViewById(R.id.SharingList_ConditionType);
            holder.SharingList_Name = view.findViewById(R.id.SharingList_Name);
            holder.SharingList_RegistDate = view.findViewById(R.id.SharingList_RegistDate);
            holder.SharingList_Keyword1 = view.findViewById(R.id.SharingList_Keyword1);
            holder.SharingList_Keyword2 = view.findViewById(R.id.SharingList_Keyword2);
            holder.SharingList_Keyword3 = view.findViewById(R.id.SharingList_Keyword3);
            holder.SharingList_Txt = view.findViewById(R.id.SharingList_Txt);
            holder.SharingList_Spinner = view.findViewById(R.id.SharingList_Spinner);
            holder.SharingList_SpinnerRL = view.findViewById(R.id.SharingList_SpinnerRL);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels * 0.10);
            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            holder.SharingList_Spinner.setAdapter(adapter);
            holder.SharingList_SpinnerRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.SharingList_Spinner.setFocusableInTouchMode(true);
                    holder.SharingList_Spinner.performClick();
                }
            });

            view.setTag(holder);

        }else
        {
            holder=(ViewHolder) view.getTag();
        }


            //TODO:항목에 맞는 데이터 뿌려줘야 함
        holder.SharingList_Spinner.setSelection(0, true);
        holder.SharingList_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int spinner_position, long id) {

                    if (spinner_position == 0) {
                        return;
                    } else {
                        spinner_position = spinner_position - 1;
                    }

                        switch (spinner_position) {
                            case 1:

                                Intent i = new Intent(context, TalentSharing_Popup_Activity.class);
                                String str = (list_ArrayList.get(position).getTalentType_CODE() == 1) ? "Give": "Take";
                                i.putExtra("TalentID", list_ArrayList.get(position).getTalentID());
                                i.putExtra("TalentFlag", str);
                                context.startActivity(i);
                                break;

                            case 2:
                                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(context);
                                AlarmDeleteDialog.setMessage("공유·관심 내역을 삭제 하시겠습니까?")
                                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Log.d("Delete Position = ", String.valueOf(position));
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
                                break;

                            case 3:
                                Intent b = new Intent(context, CustomerService_ClaimActivity.class);
                                String str2 = (list_ArrayList.get(position).getTalentType_CODE() == 1) ? "Give": "Take";
                                b.putExtra("isSelected", true);
                                b.putExtra("name", list_ArrayList.get(position).getname());
                                b.putExtra("keyword1", list_ArrayList.get(position).getKeyword1());
                                b.putExtra("keyword2", list_ArrayList.get(position).getKeyword2());
                                b.putExtra("keyword3", list_ArrayList.get(position).getKeyword3());
                                b.putExtra("myTalentID", list_ArrayList.get(position).getMyTalentID());
                                b.putExtra("tarTalentID", list_ArrayList.get(position).getTalentID());
                                b.putExtra("talentFlag", str2);
                                b.putExtra("status",list_ArrayList.get(position).getTalentConditionType_CODE());
                                context.startActivity(b);
                                break;

                        }

                    holder.SharingList_Spinner.setSelection(0, true);
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });

        holder.SharingList_Name.setText(list_ArrayList.get(position).getname());
        holder.SharingList_RegistDate.setText(list_ArrayList.get(position).getRegistDate());
        holder.SharingList_Keyword1.setText(list_ArrayList.get(position).getKeyword1());
        holder.SharingList_Keyword2.setText(list_ArrayList.get(position).getKeyword2());
        holder.SharingList_Keyword3.setText(list_ArrayList.get(position).getKeyword3());

            switch (list_ArrayList.get(position).getTalentConditionType_CODE()) {
                case 1: {
                    holder.SharingList_ConditionType.setText("[받은 관심]");
                    holder.SharingList_Txt.setText("관심을 받았습니다.");
                    break;
                }

                case 2: {
                    holder.SharingList_ConditionType.setText("[보낸 관심]");
                    holder.SharingList_Txt.setText("관심을 보냈습니다.");
                    break;
                }

                case 3: {
                    holder.SharingList_ConditionType.setText("[진행 중]");
                    holder.SharingList_Txt.setText("진행 중입니다.");
                    break;
                }
                case 4: {
                    holder.SharingList_ConditionType.setText("[공유 완료]");
                    holder.SharingList_Txt.setText("완료 하였습니다.");
                    break;
                }

                case 5: {
                    holder.SharingList_ConditionType.setText("[진행 취소]");
                    holder.SharingList_Txt.setText("진행 취소하였습니다.");
                    break;
                }
            }

        return view;
    }

    static class ViewHolder
    {

        TextView SharingList_ConditionType;
        TextView SharingList_Name;
        TextView SharingList_RegistDate;
        TextView SharingList_Keyword1;
        TextView SharingList_Keyword2;
        TextView SharingList_Keyword3;
        TextView SharingList_Txt;
        Spinner SharingList_Spinner;
        RelativeLayout SharingList_SpinnerRL;
    }



}
