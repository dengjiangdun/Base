package com.johndon.cmcc.util.caipu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView mLv;
    private List<childTop> mList;
    private CaiAdapter mAdapter;
    private static final String GET_LIST_URL = "http://apicloud.mob.com/v1/cook/category/query?key=229e227453bce";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ExpandableListView) findViewById(R.id.lv_list);
        FinalHttp finalHttp = new FinalHttp();
        finalHttp.get(GET_LIST_URL, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(final String s) {
                super.onSuccess(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dealResult(s);
                    }
                });
            }
        });
    }

    private void dealResult(String result) {
        mList = new ArrayList<>();
        String TAG = "TAG";
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("retCode");
        JSONObject resultObject = JSON.parseObject(jsonObject.getString("result"));
        if (code.equals("200")) {
           // mList = JSONArray.parseArray(resultObject.getString("childs"),childTop.class);
            JSONArray jA = JSON.parseArray(resultObject.getString("childs"));
            int size = jA.size();
            for (int i = 0 ; i < size ; ++i) {
                categoryInfo info = new categoryInfo();
                JSONObject jo = jA.getJSONObject(i);
                JSONObject jinfo = JSON.parseObject(jo.getString("categoryInfo"));
                info.setName(jinfo.getString("name"));
                JSONArray jsonArray = JSON.parseArray(jo.getString("childs"));
                int length = jsonArray.size();
                List<categoryInfo> childs = new ArrayList<>();
                for (int j = 0 ; j < length ; ++j) {
                    JSONObject jchild = jsonArray.getJSONObject(j);
                    JSONObject jj = JSON.parseObject(jchild.getString("categoryInfo"));
                    categoryInfo cateInfo = new categoryInfo();
                    cateInfo.setName(jj.getString("name"));
                    cateInfo.setCtgId(jj.getString("ctgId"));
                    childs.add(cateInfo);
                }
                childTop top = new childTop();
                top.setInfo(info);
                top.setChilds(childs);
                mList.add(top);
            }
            mAdapter = new CaiAdapter(MainActivity.this,mList);
            mLv.setAdapter(mAdapter);
        }
    }

}
