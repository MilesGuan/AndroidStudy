package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.milesguan.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjieguan on 16/9/18.
 */
public class RecyclerViewActivity extends Activity{

    private List<String> mDatas;
    MyAdapter myAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("grj" , "RecyclerViewActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, mDatas);
        recyclerView.setAdapter(myAdapter);
        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(20);
            }
        });
    }

    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        Context context;
        List<String> mDatas;

        public MyAdapter(Context context, List<String> mDatas) {
            this.context = context;
            this.mDatas = mDatas;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i("grj" , "onCreateViewHolder");
            View view = View.inflate(context , R.layout.item_recycler_view , null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));

            Object tag = holder.tv.getTag();
            Log.i("grj" , "onBindViewHolder:" + position + "    "  + tag);
            holder.tv.setTag(position);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }

}
