package com.example.MyDiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Frag1 extends Fragment {
    private View view;

    private ArrayList<DiaryData> arrayList;
    private DiaryAdapter diaryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override //fragment는 onCreateView로 함.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1,container,false); //frag1로 연결


        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        diaryAdapter = new DiaryAdapter(arrayList);
        recyclerView.setAdapter(diaryAdapter);

        Button btn_diaryadd = (Button)view.findViewById(R.id.btn_diaryadd);
        btn_diaryadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryData diaryData = new DiaryData(R.drawable.ic_launcher_background,"나의 일기","일기 내용");
                arrayList.add(diaryData);
                diaryAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
