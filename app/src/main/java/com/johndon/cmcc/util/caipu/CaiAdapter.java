package com.johndon.cmcc.util.caipu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 2017/11/28.
 */

public class CaiAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<childTop> mList;

    public CaiAdapter(Context context, List<childTop> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mList.get(i).getChilds().size();
    }

    @Override
    public Object getGroup(int i) {
        return mList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mList.get(i).getChilds().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_parent,viewGroup,false);
        }
        ((TextView)view.findViewById(R.id.tv_group)).setText(mList.get(i).getInfo().getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_parent,viewGroup,false);
        }
        ((TextView)view.findViewById(R.id.tv_group)).setText(mList.get(i).getChilds().get(i1).getName());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
