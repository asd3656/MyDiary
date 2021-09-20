package com.example.MyDiary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Frag4 extends Fragment {
    private View view2;

    static class Point {
        float x;
        float y;
        boolean check;
        int color;

        public Point(float x, float y, boolean check, int color) {
            this.x = x;
            this.y = y;
            this.check = check;
            this.color = color;
        }
    }
    class MyView extends View
    {
        public MyView(Context context) { super(context); }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setStrokeWidth(15);
            for(int i=1 ; i<points.size() ; i++)
            {
                p.setColor(points.get(i).color);
                if(!points.get(i).check)
                    continue;
                canvas.drawLine(points.get(i-1).x,points.get(i-1).y,points.get(i).x,points.get(i).y,p);
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    points.add(new activity_draw.Point(x,y,false , color));
                case MotionEvent.ACTION_MOVE :
                    points.add(new activity_draw.Point(x,y,true , color));
                    break;
                case MotionEvent.ACTION_UP :
                    break;
            }
            invalidate();
            return true;
        }
    }

    ArrayList<activity_draw.Point> points = new ArrayList<activity_draw.Point>();
    Button draw_red_btn,draw_blue_btn,draw_black_btn,clear_btn;
    LinearLayout drawlinear;
    int color = Color.BLACK;

    @Nullable
    @Override //fragment는 onCreateView로 함.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.frag4,container,false); //frag4로 연결

        final MyView m = new MyView(getActivity());
        /* ----- 색 변경 ------ */
        view2.findViewById(R.id.draw_red_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.RED ;
            }
        });
        view2.findViewById(R.id.draw_blue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLUE ;
            }
        });
        view2.findViewById(R.id.draw_black_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLACK ;
            }
        });

        clear_btn = view2.findViewById(R.id.clear_btn);
        drawlinear = view2.findViewById(R.id.draw_linear);
        clear_btn.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v){
                points.clear();
                m.invalidate();
            }
        });
        drawlinear.addView(m);

        return view2;
    }
}