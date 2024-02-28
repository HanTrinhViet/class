package net.braniumacademy.lab_02.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.braniumacademy.lab_02.R;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvContactName.setText(contact.getName());
        holder.tvContactPhoneNumber.setText(contact.getPhoneNumber());
        holder.ivContactImage.setImageURI(Uri.parse(contact.getImageUri()));
        holder.checkBoxContactStatus.setChecked(contact.getStatus());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox checkBoxContactStatus;
        TextView tvContactName;
        TextView tvContactPhoneNumber;
        ImageView ivContactImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxContactStatus = itemView.findViewById(R.id.check_box_contact_status);
            tvContactName = itemView.findViewById(R.id.tv_contact_name);
            tvContactPhoneNumber = itemView.findViewById(R.id.tv_contact_number);
            ivContactImage = itemView.findViewById(R.id.iv_contact_image);
            checkBoxContactStatus.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.check_box_contact_status) {
                Contact currentContact = contacts.get(getAdapterPosition());
                Toast.makeText(itemView.getContext(), "Position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                currentContact.setStatus(checkBoxContactStatus.isChecked());
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
