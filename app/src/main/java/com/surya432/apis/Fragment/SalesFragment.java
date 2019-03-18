package com.surya432.apis.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.apis.Activity.Sales.SalesGEOTagActivity;
import com.surya432.apis.Activity.Sales.SalesListCustomerActivity;
import com.surya432.apis.Activity.Sales.SalesRealisasiKegiatanActivity;
import com.surya432.apis.Activity.Sales.SalesPlaningKegiatanActivity;
import com.surya432.apis.Helpers.SessionManager;
import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SalesFragment extends Fragment {

    @BindView(R.id.PlanKegiatan)
    LinearLayout planKegiatan;
    @BindView(R.id.namaPegawai)
    TextView namaPegawai;
    @BindView(R.id.jabatanPegawai)
    TextView jabatanPegawai;
    @BindView(R.id.salesRealisasi)
    LinearLayout salesRealisasi;
    @BindView(R.id.salesGeoTag)
    LinearLayout salesGeoTag;
    @BindView(R.id.SaleslistCustomer)
    LinearLayout SaleslistCustomer;
    private Intent intent;
    private SessionManager sessionManager;
    public SalesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getContext());
        firstLoad();
        return view;
    }

    private void firstLoad() {
        namaPegawai.setText(sessionManager.getName());
        jabatanPegawai.setText(sessionManager.getJob());
        planKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesPlaningKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        salesRealisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesRealisasiKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        salesGeoTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesGEOTagActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        SaleslistCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesListCustomerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
    }


}
