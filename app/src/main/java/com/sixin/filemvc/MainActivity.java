package com.sixin.filemvc;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.sixin.filemvc.utils.LogUtils;
import com.sixin.filemvc.utils.PermissionUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements PermissionUtils.OnPermissionListener, BaseQuickAdapter.OnItemClickListener {

    private Unbinder mUnbinder;

    @BindView(R.id.rlv_file)
    RecyclerView mRlvFile;

    @BindView(R.id.msv_file)
    MultipleStatusView mMsvFile;

    private FileManager mFileManager;
    private FileAdapter mAdapter;
    private List<File> mFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        mFileManager = new FileManager();

        initRlv();

        String[] requestPermissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        PermissionUtils.requestPermissions(this,
                Config.CODE_REQUEST_PERMISSIONS,
                requestPermissions,
                this,
                new PermissionUtils.RationaleHandler(){

                    @Override
                    protected void showRationale() {
                        LogUtils.d("showRationale");
                        requestPermissionsAgain();
                    }
                });
    }

    private void initRlv() {
        mRlvFile.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(MainActivity.this,
                requestCode,
                permissions,
                grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        PermissionUtils.releaseListener();
        App.getRefWatcher(getApplicationContext()).watch(this);
    }

    @Override
    public void onPermissionGranted() {
        LogUtils.d("权限申请成功");
        showLoading();
        mFiles = mFileManager.readFiles();
        if (mFiles != null && mFiles.size() > 0) {
            showContent();
        }else{
            showEmpty();
        }
    }

    @Override
    public void onPermissionDenied(String[] deniedPermissions) {
        LogUtils.d("权限被拒绝");
        Toast.makeText(this, "无权限读取本地数据", Toast.LENGTH_SHORT).show();
        showEmpty();
    }

    public void showEmpty() {
        if (mMsvFile != null) {
            mMsvFile.showEmpty();
        }
    }

    public void showLoading() {
        if (mMsvFile != null) {
            mMsvFile.showLoading();
        }
    }

    public void showContent() {
        if (mMsvFile != null) {
            LogUtils.d("showContent");
            mAdapter = new FileAdapter(R.layout.item_file1, mFiles);
            mRlvFile.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
            mMsvFile.showContent();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mFiles != null && mFiles.size() > 0 && mFileManager != null) {
            boolean result = mFileManager.deleteFile(mFiles.get(position));
            if (result) {
                removeItem(position);
            }else{
                Toast.makeText(this, "文件删除失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void removeItem(int deletePosition) {
        if (mAdapter != null && deletePosition >=0) {
            mAdapter.remove(deletePosition);
        }
    }
}
