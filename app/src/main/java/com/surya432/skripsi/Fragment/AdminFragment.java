package com.surya432.skripsi.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.skripsi.Activity.CreateTiketActivity;
import com.surya432.skripsi.Activity.ListArticleActivity;
import com.surya432.skripsi.Activity.ListTiketActivity;
import com.surya432.skripsi.Activity.LoginActivity;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment {


    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.namaPegawai)
    TextView namaPegawai;
    @BindView(R.id.jabatanPegawai)
    TextView jabatanPegawai;
    @BindView(R.id.ListTiket)
    LinearLayout listTiket;
    @BindView(R.id.CreateTiket)
    LinearLayout createTiket;
    @BindView(R.id.ListArticle)
    LinearLayout ListArticle;
    @BindView(R.id.akun)
    LinearLayout akun;
    @BindView(R.id.logOut)
    LinearLayout logOut;
    @BindView(R.id.menu)
    CardView menu;
    private SessionManager sessionManager;

    public AdminFragment() {
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

    private Intent intent;

    private void firstLoad() {
        namaPegawai.setText(sessionManager.getName());
        profileImage.setImageBitmap(ToolUtil.Base64ToBitmap(sessionManager.getAvatar()));
        jabatanPegawai.setText(sessionManager.getRole());
        listTiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), ListTiketActivity.class);
                getContext().startActivity(intent);
            }
        });
        createTiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), CreateTiketActivity.class);
                getContext().startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.destroySession();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
