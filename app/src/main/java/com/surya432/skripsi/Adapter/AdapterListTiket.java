package com.surya432.skripsi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.surya432.skripsi.Activity.DetailTiketActivity;
import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AdapterListTiket extends RecyclerView.Adapter<AdapterListTiket.MyViewHoder> {
    private Context context;
    private List<ModelListTiket.DataBean> listTiketData;

    public AdapterListTiket(Context context, List<ModelListTiket.DataBean> listItem) {
        this.context = context;
        this.listTiketData = listItem;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_tiket, viewGroup, false);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder myViewHoder, int i) {
        ModelListTiket.DataBean item = listTiketData.get(i);
        myViewHoder.tvDepartement.setText(item.getDepartementName());
        myViewHoder.tvPrioritas.setText(item.getPrioritasName());
        myViewHoder.tvStatus.setText(item.getStatusName());
        myViewHoder.tvUpdateAt.setText(item.getUpdated_at());
        myViewHoder.TvSubject.setText(item.getSubject());
        myViewHoder.rowContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelListTiket.DataBean m = listTiketData.get(i);
                Intent intent = new Intent(context, DetailTiketActivity.class);
                intent.putExtra("Data", m);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //Toast.makeText(context, "SDSD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTiketData == null ? 0 : listTiketData.size();
    }



    class MyViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.TvSubject)
        TextView TvSubject;
        @BindView(R.id.tvUpdateAt)
        TextView tvUpdateAt;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvDepartement)
        TextView tvDepartement;
        @BindView(R.id.tvPrioritas)
        TextView tvPrioritas;
        @BindView(R.id.rowContent)
        LinearLayout rowContent;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
