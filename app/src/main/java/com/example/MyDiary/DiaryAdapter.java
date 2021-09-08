package com.example.MyDiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.CustomViewHolder>{

    private ArrayList<DiaryData> arrayList;

    public DiaryAdapter(ArrayList<DiaryData> arrayList) {
        this.arrayList = arrayList;
    }

    //생명주기
    @NonNull
    @Override
    public DiaryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diaryitem_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    //실제 추가될때
    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.CustomViewHolder holder, int position) {

        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_dytitle.setText(arrayList.get(position).getTv_dytitle());
        holder.tv_dycontent.setText(arrayList.get(position).getTv_dycontent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.tv_dytitle.getText().toString(); //string형태로 변환 클릭한 내용의 이름을 가져옴
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    //삭제버튼
    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_dytitle;
        protected TextView tv_dycontent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_dytitle = (TextView) itemView.findViewById(R.id.tv_dytitle);
            this.tv_dycontent = (TextView) itemView.findViewById(R.id.tv_dycontent);
        }
    }
}
