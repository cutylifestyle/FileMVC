package com.sixin.filemvc;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sixin.filemvc.utils.FileUtils;
import com.sixin.filemvc.utils.FormatUtils;

import java.io.File;
import java.util.List;

public class FileAdapter extends MiddleLayerAdapter<File> {
    //TODO 泛型规则存在问题
    //TODO 如何配合BaseQuickAdapter使用----->顺便研究一下RecyclerView的源码
    public FileAdapter(int layoutResId, @Nullable List<File> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        ImageView imgFileIcon =  helper.getView(R.id.img_file_icon);
        TextView tvFileName = helper.getView(R.id.tv_file_name);
        TextView tvFileSize = helper.getView(R.id.tv_file_size);

        int res = FormatUtils.getFileIcon(item);
        imgFileIcon.setImageResource(res);

        String fileName = getContent(item.getName(),1);
        tvFileName.setText(fileName);

        String fileSize = getContent(FileUtils.getFileSize(item),2);
        tvFileSize.setText(fileSize);

    }
}
