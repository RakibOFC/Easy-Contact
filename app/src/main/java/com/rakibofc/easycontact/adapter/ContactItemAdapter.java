package com.rakibofc.easycontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.model.ContactData;

import java.util.List;

public class ContactItemAdapter extends RecyclerView.Adapter<ContactItemAdapter.ViewHolder> {

    private final Context context;
    private final List<ContactData> contactDataItems;
    private final OnItemClickListener listener;

    public ContactItemAdapter(Context context, List<ContactData> contactDataItems, OnItemClickListener listener) {

        this.context = context;
        this.contactDataItems = contactDataItems;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ContactData item);
    }

    @NonNull
    @Override
    public ContactItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItemAdapter.ViewHolder holder, int position) {

        ContactData contactData = contactDataItems.get(position);
        holder.bind(contactData, listener);
    }

    @Override
    public int getItemCount() {
        return contactDataItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivContactImage;
        private final TextView tvContactName;
        private final TextView tvContactNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivContactImage = itemView.findViewById(R.id.iv_user_logo);
            tvContactName = itemView.findViewById(R.id.tv_contact_name);
            tvContactNo = itemView.findViewById(R.id.tv_contact_no);
        }

        public void bind(final ContactData contactData, final OnItemClickListener listener) {

            ivContactImage.setImageBitmap(contactData.getContactImage());
            tvContactName.setText(contactData.getContactName());
            tvContactNo.setText(contactData.getContactNumber());

            itemView.setOnClickListener(v -> listener.onItemClick(contactData));
        }
    }
}
