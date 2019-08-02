package com.surya432.skripsi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.skripsi.Activity.DetailArtikelActivity;
import com.surya432.skripsi.Model.ModelArtikel;
import com.surya432.skripsi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.MyViewHoder> {


    private Context context;
    private List<ModelArtikel.DataBean> list;

    public AdapterArtikel(Context context, List<ModelArtikel.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_artikel, viewGroup, false);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder myViewHoder, int i) {
        myViewHoder.TvSubject.setText(list.get(i).getName());
      /*  Spanned policy = Html.fromHtml(list.get(i).getBody());
        myViewHoder.TvSubject.setText(policy);*/
        myViewHoder.tvCreatedBy.setText(list.get(i).getCreated_by());
        myViewHoder.tvUpdateAt.setText(list.get(i).getUpdated_at());
        myViewHoder.rowContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelArtikel.DataBean m = list.get(i);
                Intent intent = new Intent(context, DetailArtikelActivity.class);
                intent.putExtra("Data", m);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.TvSubject)
        TextView TvSubject;
        @BindView(R.id.tvCreated_by)
        TextView tvCreatedBy;
        @BindView(R.id.tvUpdateAt)
        TextView tvUpdateAt;
        @BindView(R.id.rowContent)
        LinearLayout rowContent;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
