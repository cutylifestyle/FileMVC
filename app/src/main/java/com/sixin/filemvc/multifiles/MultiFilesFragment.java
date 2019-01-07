package com.sixin.filemvc.multifiles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.sixin.filemvc.App;
import com.sixin.filemvc.FileAdapter;
import com.sixin.filemvc.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiFilesFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {

    private static final String ARG_PARAM = "title";
    private String mTitle;
    private Unbinder mUnbinder;

    @BindView(R.id.rlv_multi_files)
    RecyclerView mRlvMultiFiles;

    @BindView(R.id.msv_file)
    MultipleStatusView mMsvFile;

    private FileAdapter mAdapter;

    public MultiFilesFragment() {
        // Required empty public constructor
    }

    public static MultiFilesFragment newInstance(String title) {
        MultiFilesFragment fragment = new MultiFilesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multi_files, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        mRlvMultiFiles.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FileAdapter(R.layout.item_file1, new ArrayList<>());
        mRlvMultiFiles.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        App.getRefWatcher(getContext().getApplicationContext()).watch(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
