package com.example.MyDiary;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FriendRequest extends StringRequest {

    final static private String URL = "http://asd3656.dothome.co.kr/FriendRegister.php";
    private Map<String, String> map;

    public FriendRequest(String user_nick,String frd_nick, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_nick",user_nick);
        map.put("frd_nick",frd_nick);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
