package com.surya432.apis.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surya432.apis.Helpers.SessionManager;
import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SalesFragment extends Fragment {

    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @BindView(R.id.namaPegawai)
    TextView namaPegawai;
       @BindView(R.id.jabatanPegawai)
    TextView jabatanPegawai;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getContext());
        firstLoad();
        return view;
    }

    private void firstLoad() {
        namaPegawai.setText(sessionManager.getName());
        jabatanPegawai.setText(sessionManager.getJob());
    }


}
