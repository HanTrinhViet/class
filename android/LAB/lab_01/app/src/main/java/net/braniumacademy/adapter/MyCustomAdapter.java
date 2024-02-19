package net.braniumacademy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.braniumacademy.Person;
import net.braniumacademy.R;

import java.util.List;

public class MyCustomAdapter extends BaseAdapter {
    private List<Person> personList;
    private Context context;

    public MyCustomAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_custom_adapter,
                    parent,
                    false
            );
            viewHolder = new ViewHolder();
            viewHolder.imgVwPersonAvatar = convertView.findViewById(R.id.img_vw_person_avatar);
            viewHolder.txtVwPersonInfo = convertView.findViewById(R.id.txt_vw_person_info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgVwPersonAvatar.setImageResource(R.mipmap.ic_avatar_foreground);
        viewHolder.txtVwPersonInfo.setText(personList.get(position).toString());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtVwPersonInfo;
        ImageView imgVwPersonAvatar;
    }
}

