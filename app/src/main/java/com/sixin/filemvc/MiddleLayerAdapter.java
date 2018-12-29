package com.sixin.filemvc;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class MiddleLayerAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder>{

    public MiddleLayerAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public MiddleLayerAdapter(@Nullable List<T> data) {
        super(data);
    }

    public MiddleLayerAdapter(int layoutResId) {
        super(layoutResId);
    }

    /**
     * @param code 1: 暂无文件名称  2: 暂无文件大小
     * */
    protected String getContent(String content,int code) {
        String result = null;

        if (!TextUtils.isEmpty(content)) {
            result = content;
            return result;
        }

        switch (code) {
            case 1:
                result = "暂无文件名称";
                break;
            case 2:
                result = "暂无文件大小";
                break;
            default:
                result = "暂无信息";
                break;
        }
        return result;
    }
}
