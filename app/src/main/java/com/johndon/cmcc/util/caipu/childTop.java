package com.johndon.cmcc.util.caipu;

import java.util.List;

/**
 * Created by DELL on 2017/11/27.
 */

public class childTop {
    private categoryInfo info;
    List<categoryInfo> childs;

    public List<categoryInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<categoryInfo> childs) {
        this.childs = childs;
    }

    public categoryInfo getInfo() {
        return info;
    }

    public void setInfo(categoryInfo info) {
        this.info = info;
    }
}
