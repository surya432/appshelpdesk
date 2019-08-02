package com.surya432.skripsi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.surya432.skripsi.Model.ModelArtikel;
import com.surya432.skripsi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailArtikelActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvHead)
    TextView tvHead;
    @BindView(R.id.tvcreatedBy)
    TextView tvcreatedBy;
    @BindView(R.id.tvContent)
    TextView tvContent;
    private ModelArtikel.DataBean m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("Data")) {
            m = getIntent().getParcelableExtra("Data");
            //ToolUtil.BuildAlertDialog(DetailTiketActivity.this, m.getId()+"");
            getSupportActionBar().setTitle(m.getName());
            firstLoad();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void firstLoad() {
        tvHead.setText(m.getName().trim());
        Spanned sp = Html.fromHtml(m.getBody());
        tvContent.setText(sp);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvcreatedBy.setText(m.getCreated_by());

    }
}
