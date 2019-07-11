package com.surya432.skripsi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
