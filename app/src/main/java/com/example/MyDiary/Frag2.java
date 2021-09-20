package com.example.MyDiary;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Frag2 extends Fragment {
    private View view;

    @Nullable
    @Override //fragment는 onCreateView로 함.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2,container,false); //frag2로 연결

        //아이디 값 찾아주기
        Bundle bundle = getArguments(); //MainActivity에서 가져오기
        String userNick = bundle.getString("userNick");
        EditText et_friendadd = (EditText)view.findViewById(R.id.et_friendadd);

        //회원가입 버튼 클릭 시 수행
        Button btn_friendadd = (Button)view.findViewById(R.id.btn_friendadd);
        btn_friendadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get해온다.
                String user_nick= userNick;
                String frd_nick = et_friendadd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 친구추가에 성공한경우
                                Toast.makeText( getActivity().getApplicationContext(), "친구추가에 성공하였습니다", Toast.LENGTH_SHORT).show();
                            } else { // 친구추가에 실패한 경우
                                Toast.makeText(getActivity().getApplicationContext(), "친구추가에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 Volley를 이용해서 요청함.
                FriendRequest friendRequest = new FriendRequest(user_nick,frd_nick, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(friendRequest);

            }
        });
        return view;
    }
}