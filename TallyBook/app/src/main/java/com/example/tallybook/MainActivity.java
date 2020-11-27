package com.example.tallybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    private List<ListInfo> list = new ArrayList<ListInfo>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData() ;
        itemClick();

    }

    private void initData() {
        listView = findViewById(R.id.list) ;

        String week_date = DataTime.getFirstDayOfWeek(DataTime.getDate('w')) + " - " + DataTime.getLastDayOfWeek(DataTime.getDate('w')) ;
        String month_date = DataTime.getDate('M') + "月" + "1日 - " + DataTime.getDate('M') + "月" + DataTime.getMonthOfDay(DataTime.getDate('Y'),DataTime.getDate('M')) + "日" ;
        String year_data = DataTime.getDate('Y') + "年" ;
        list.add(new ListInfo("今天","还没有记过账","0.00","0.00",R.mipmap.list_item_today,R.mipmap.arrow)) ;
        list.add(new ListInfo("本周",week_date,"0.00","0.00",R.mipmap.list_item_week,R.mipmap.arrow)) ;
        list.add(new ListInfo("本月",month_date,"0.00","0.00",R.mipmap.list_item_month,R.mipmap.arrow)) ;
        list.add(new ListInfo("本年",year_data,"0.00","0.00",R.mipmap.list_item_year,R.mipmap.arrow)) ;
        MyListDataAdapter myListDataAdapter = new MyListDataAdapter() ;
        listView.setAdapter(myListDataAdapter) ;

//        hour = calendar.get(Calendar.HOUR_OF_DAY);
//        minute = calendar.get(Calendar.MINUTE);
//        second = calendar.get(Calendar.SECOND);
    }



    private void itemClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this , "day" , Toast.LENGTH_SHORT).show() ;
                        break ;
                    case 1:
                        Toast.makeText(MainActivity.this , "week" , Toast.LENGTH_SHORT).show() ;
                        break ;
                    case 2:
                        Toast.makeText(MainActivity.this , "month" , Toast.LENGTH_SHORT).show() ;
                        break ;
                    case 3:
                        Toast.makeText(MainActivity.this , "year" , Toast.LENGTH_SHORT).show() ;
                        break ;
                }
            }
        });
    }


    class MyListDataAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyListViewHolder viewHolder ;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this , R.layout.list_main , null) ;
                viewHolder = new MyListViewHolder() ;
                viewHolder.list_item_img = convertView.findViewById(R.id.list_item_img) ;
                viewHolder.list_item_time = convertView.findViewById(R.id.list_item_time) ;
                viewHolder.list_item_info = convertView.findViewById(R.id.list_item_info) ;
                viewHolder.list_item_income = convertView.findViewById(R.id.list_item_income) ;
                viewHolder.list_item_payout = convertView.findViewById(R.id.list_item_payout) ;
                viewHolder.list_item_arrow = convertView.findViewById(R.id.list_item_arrow) ;
                convertView.setTag(viewHolder) ;
            }else{
                viewHolder = (MyListViewHolder)convertView.getTag();
            }
            ListInfo listInfo = list.get(position) ;
            viewHolder.list_item_img.setImageResource(listInfo.getIcon_front()) ;
            viewHolder.list_item_time.setText(listInfo.getTime()) ;
            viewHolder.list_item_info.setText(listInfo.getInfo()) ;
            viewHolder.list_item_income.setText(listInfo.getIncome()) ;
            viewHolder.list_item_payout.setText(listInfo.getPayout()) ;
            viewHolder.list_item_arrow.setImageResource(listInfo.getIcon_last()) ;

            return convertView;
        }

        class MyListViewHolder{
            TextView list_item_time , list_item_info , list_item_income , list_item_payout ;
            ImageView list_item_img , list_item_arrow ;
        }
    }
}

