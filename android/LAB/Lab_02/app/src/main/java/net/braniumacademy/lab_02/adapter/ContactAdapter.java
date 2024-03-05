package net.braniumacademy.lab_02.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import net.braniumacademy.lab_02.R;
import net.braniumacademy.lab_02.databinding.ItemContactLayoutBinding;
import net.braniumacademy.lab_02.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_layout, parent, false);
        ItemContactLayoutBinding itemContactLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_contact_layout,
                parent,
                false
        );

        return new ViewHolder(itemContactLayoutBinding);
//        return new ViewHolder(in);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.itemContactLayoutBinding.setContact(contact);
        holder.itemContactLayoutBinding.ivContactImage.setImageURI(Uri.parse(contact.getImageUri()));
        holder.itemContactLayoutBinding.tvContactName.setText(contact.getName());
        holder.itemContactLayoutBinding.tvContactNumber.setText(contact.getPhoneNumber());
        holder.itemContactLayoutBinding.checkBoxContactStatus.setChecked(contact.getStatus());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemContactLayoutBinding itemContactLayoutBinding;

        public ViewHolder(ItemContactLayoutBinding itemContactLayoutBinding) {
            super(itemContactLayoutBinding.getRoot());
            this.itemContactLayoutBinding = itemContactLayoutBinding;
            itemContactLayoutBinding.ivContactImage.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.check_box_contact_status) {
                Contact currentContact = contacts.get(getAdapterPosition());
                Toast.makeText(itemView.getContext(), "Position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                currentContact.setStatus(itemContactLayoutBinding.checkBoxContactStatus.isChecked());
            } else if (v.getId() == itemView.getId()) {
                Toast.makeText(itemView.getContext(), "Position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
