package com.surya432.skripsi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surya432.skripsi.Model.ModelContentTiket;
import com.surya432.skripsi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;


    private Context mContext;
    private List<ModelContentTiket.DataBean> mMessageList;

    public MessageListAdapter(Context context, List<ModelContentTiket.DataBean> list) {
        this.mContext = context;
        this.mMessageList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if (i == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_message_sent, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (i == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_message_received, viewGroup, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == VIEW_TYPE_MESSAGE_SENT) {
            ((SentMessageHolder) viewHolder).textMessageBody.setText(Html.fromHtml(mMessageList.get(i).getBody()));
            ((SentMessageHolder) viewHolder).textMessageTime.setText(mMessageList.get(i).getCreated_at());
        } else {
            ((ReceivedMessageHolder) viewHolder).textMessageBodyReceived.setText(Html.fromHtml(mMessageList.get(i).getBody()));
            ((ReceivedMessageHolder) viewHolder).textMessageTimeReceived.setText(mMessageList.get(i).getCreated_at());
            ((ReceivedMessageHolder) viewHolder).textMessageNameReceived.setText(mMessageList.get(i).getSenders());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessageList.get(position).getRepply() == 0) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_message_name)
        TextView textMessageNameReceived;
        @BindView(R.id.text_message_body)
        TextView textMessageBodyReceived;
        @BindView(R.id.text_message_time)
        TextView textMessageTimeReceived;

        public ReceivedMessageHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class SentMessageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_message_body)
        TextView textMessageBody;
        @BindView(R.id.text_message_time)
        TextView textMessageTime;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
