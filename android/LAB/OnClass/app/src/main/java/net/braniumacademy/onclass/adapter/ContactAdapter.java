package net.braniumacademy.onclass.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.databinding.ItemContactLayoutBinding;
import net.braniumacademy.onclass.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private List<Contact> contactList;
    public static int index;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactLayoutBinding itemContactLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_contact_layout,
                parent,
                false
        );
        return new ContactViewHolder(itemContactLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.itemContactLayoutBinding.setContact(contact);
        holder.itemContactLayoutBinding.ivContactImage.setImageURI(Uri.parse(contact.getImagePath()));
        holder.itemContactLayoutBinding.tvContactName.setText(contact.getName());
        holder.itemContactLayoutBinding.tvContactNumber.setText(contact.getNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnLongClickListener {
        ItemContactLayoutBinding itemContactLayoutBinding;

        public ContactViewHolder(ItemContactLayoutBinding itemContactLayoutBinding) {
            super(itemContactLayoutBinding.getRoot());
            this.itemContactLayoutBinding = itemContactLayoutBinding;
            itemContactLayoutBinding.getRoot().setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public boolean onLongClick(View v) {
            index = getAdapterPosition();
            return true;
        }
    }

}
