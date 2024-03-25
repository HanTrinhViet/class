package net.braniumacademy.onclass.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.constants.Constants;
import net.braniumacademy.onclass.databinding.ItemContactLayoutBinding;
import net.braniumacademy.onclass.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Filterable {
    private Context context;
    private List<Contact> contactList;
    private List<Contact> backUpContactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();

                // Nếu backup data trống
                if (backUpContactList == null) {
                    backUpContactList = new ArrayList<>(contactList);
                }

                // Nếu chuỗi rỗng thì khởi tạo giá trị
                if (charSequence == null || charSequence.length() == 0) {
                    fr.count = backUpContactList.size();
                    fr.values = backUpContactList;
                } else { // nếu không rỗng thì...
                    List<Contact> newData = new ArrayList<>();
                    for (Contact c : contactList) {
                        if (c.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            newData.add(c);
                        }
                    }
                    fr.values = newData;
                    fr.count = newData.size();
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactList = new ArrayList<>();
                List<Contact> tmp = (ArrayList<Contact>) results.values;
                contactList.addAll(tmp);
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_contact_layout,
                parent,
                false
        );
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.binding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ItemContactLayoutBinding binding;

        public ContactViewHolder(ItemContactLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Edit");
            menu.add(getAdapterPosition(), 1, 1, "Delete");
            menu.add(getAdapterPosition(), 2, 2, "Call");
            menu.add(getAdapterPosition(), 3, 3, "Message");
        }

    }

}
