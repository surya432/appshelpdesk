package com.surya432.apis.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.apis.Activity.Sales.SalesComplainActivity;
import com.surya432.apis.Activity.Sales.SalesGEOTagActivity;
import com.surya432.apis.Activity.Sales.SalesListCustomerActivity;
import com.surya432.apis.Activity.Sales.SalesPlaningKegiatanActivity;
import com.surya432.apis.Activity.Sales.SalesRealisasiKegiatanActivity;
import com.surya432.apis.Activity.Sales.SalesReviewComplainActivity;
import com.surya432.apis.Activity.Sales.SalesReviewKegiatanActivity;
import com.surya432.apis.Activity.Sales.SalesReviewUpdateActivity;
import com.surya432.apis.Activity.Sales.SalesUpdateKegiatanActivity;
import com.surya432.apis.Helpers.SessionManager;
import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SalesFragment extends Fragment {
    @OnClick(R.id.SalesReviewComplain)
    void SalesReviewComplainClick (){
        intent = new Intent(getContext(), SalesReviewComplainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
    @OnClick(R.id.SalesReviewUpdate)
    void SalesReviewUpdateClick (){
        intent = new Intent(getContext(), SalesReviewUpdateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
    @BindView(R.id.PlanKegiatan)
    LinearLayout planKegiatan;
    @BindView(R.id.namaPegawai)
    TextView namaPegawai;
    @BindView(R.id.jabatanPegawai)
    TextView jabatanPegawai;
    @BindView(R.id.salesRealisasi)
    LinearLayout salesRealisasi;
    @BindView(R.id.SalesUpdateKegiatan)
    LinearLayout SalesUpdateKegiatan;
    @BindView(R.id.salesGeoTag)
    LinearLayout salesGeoTag;
    @BindView(R.id.SaleslistCustomer)
    LinearLayout SaleslistCustomer;
    @BindView(R.id.SalesComplainList)
    LinearLayout SalesComplainList;
    @BindView(R.id.SalesReviewRealisasi)
    LinearLayout SalesReviewRealisasi;
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

        SalesUpdateKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesUpdateKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        SalesComplainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesComplainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        SalesReviewRealisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SalesReviewKegiatanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

    }


}
