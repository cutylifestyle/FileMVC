package com.sixin.filemvc.multifiles;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sixin.filemvc.Config;
import com.sixin.filemvc.R;
import com.sixin.filemvc.utils.LogUtils;
import com.sixin.filemvc.utils.PermissionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MultiFilesActivity extends AppCompatActivity implements PermissionUtils.OnPermissionListener {

    private Unbinder mUnbinder;

    @BindView(R.id.tab_multi_files)
    TabLayout mTabMultiFiles;

    @BindView(R.id.vp_multi_files)
    ViewPager mVPMultiFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_files);
        mUnbinder = ButterKnife.bind(this);
        String[] requestPermissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        PermissionUtils.releaseListener();
    }

    @Override
    public void onPermissionGranted() {
        init();
    }

    @Override
    public void onPermissionDenied(String[] deniedPermissions) {
        LogUtils.d("权限被拒绝");
        Toast.makeText(this, "无权限读取本地数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(MultiFilesActivity.this,
                requestCode,
                permissions,
                grantResults);
    }

    private void init() {
        String[] titles = new String[]{"txt", "png"};

        for (String title : titles) {
            mTabMultiFiles.addTab(mTabMultiFiles.newTab());
        }

        MultiFilesAdapter adapter = new MultiFilesAdapter(titles, getSupportFragmentManager());
        mVPMultiFiles.setAdapter(adapter);
        mTabMultiFiles.setupWithViewPager(mVPMultiFiles);

        for (int i = 0 ; i < titles.length ; i++) {
            mTabMultiFiles.getTabAt(i).setText(titles[i]);
        }
    }
}
