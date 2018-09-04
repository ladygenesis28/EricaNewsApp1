package com.example.ladyg.ericanewsappstage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<News> mData;

    private List<News> newsList;

    public RecyclerViewAdapter(Context mContext, List<News> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    public RecyclerViewAdapter (List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View view;
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        //view = inflater.inflate(R.layout.news_row_item,parent,false);

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.news_sectionname.setText(mData.get(position).getSectionName());
        holder.news_type.setText(mData.get(position).getType());
        holder.news_sectionid.setText(mData.get(position).getSectionId());
        holder.news_date.setText(mData.get(position).getWebPublicationDate());
        holder.news_title.setText(mData.get(position).getWebTitle());


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void clear() {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView news_title;
        TextView news_sectionname;
        TextView news_type;
        TextView news_sectionid;
        TextView news_date;


        public MyViewHolder(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.news_title);
            news_type = itemView.findViewById(R.id.type);
            news_sectionid = itemView.findViewById(R.id.sectionid);
            news_date = itemView.findViewById(R.id.date);
            news_sectionname = itemView.findViewById(R.id.section_name);
        }
    }

}
