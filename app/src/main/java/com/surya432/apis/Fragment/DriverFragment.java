package com.surya432.apis.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.apis.Activity.Driver.DriverPlaningKegiatanActivity;
import com.surya432.apis.Activity.Driver.DriverRealisasiKegiatanActivity;
import com.surya432.apis.Helpers.SessionManager;
import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverFragment extends Fragment {


    @BindView(R.id.namaPegawai)
    TextView namaPegawai;
    @BindView(R.id.jabatanPegawai)
    TextView jabatanPegawai;
    @BindView(R.id.driverRealisasiKegiatan)
    LinearLayout driverRealisasiKegiatan;
    private SessionManager sessionManager;

    @BindView(R.id.planPengiriman)
    LinearLayout planPengiriman;
    public DriverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getContext());
        firstLoad();
        return view;
    }

    private void firstLoad() {
        namaPegawai.setText(sessionManager.getName());
        jabatanPegawai.setText(sessionManager.getJob());
        planPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DriverPlaningKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        driverRealisasiKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DriverRealisasiKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
    }


}
