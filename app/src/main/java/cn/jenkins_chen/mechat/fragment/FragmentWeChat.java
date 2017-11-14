package cn.jenkins_chen.mechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jenkins_chen.mechat.R;

/**
 * Created by chenx on 2017/10/29.
 */

public class FragmentWeChat extends Fragment {

    View view = null;
    SimpleAdapter adapter;
   int[] imgs = {R.mipmap.wn,R.mipmap.wo,R.mipmap.wp,R.mipmap.wo,R.mipmap.wp,R.mipmap.wo,R.mipmap.wp,R.mipmap.wn,R.mipmap.wo,R.mipmap.wp,R.mipmap.wo,R.mipmap.wp,R.mipmap.wo,R.mipmap.wp};
    String[] users = {"麻花藤","佳悦听","丽艳哄","佳悦听","丽艳哄","佳悦听","丽艳哄","麻花藤","佳悦听","丽艳哄","佳悦听","丽艳哄","佳悦听","丽艳哄"};
    String[] digests = {"充钱吧少年！！","我会造车233333","竞价排名？妙啊","我会造车233333","竞价排名？妙啊","我会造车233333","竞价排名？妙啊","充钱吧少年！！","我会造车233333","竞价排名？妙啊","我会造车233333","竞价排名？妙啊","我会造车233333","竞价排名？妙啊"};
    ArrayList<Map<String,Object>> data = new ArrayList<>();
    int count = 14;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_wechat,container,false);

        ListView lv_wechat = view.findViewById(R.id.lv_wechat);

        for(int i=0;i<count;i++)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("lv_img_user",imgs[i]);
            map.put("lv_tv_uname",users[i]);
            map.put("lv_tv_digest",digests[i]);
            data.add(map);
        }
        if(adapter==null)
        {
            adapter = new SimpleAdapter(view.getContext(),data,R.layout.list_item_wechat,new String[]{"lv_img_user","lv_tv_uname","lv_tv_digest"},new int[]{R.id.lv_img_user,R.id.lv_tv_uname,R.id.lv_tv_digest});
        }
        lv_wechat.setAdapter(adapter);

        return view;
    }

}
