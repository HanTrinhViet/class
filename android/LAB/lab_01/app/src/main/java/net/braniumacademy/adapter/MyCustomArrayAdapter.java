package net.braniumacademy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.braniumacademy.Person;
import net.braniumacademy.R;

import java.util.List;

public class MyCustomArrayAdapter extends ArrayAdapter<Person> {
    private List<Person> personList;
    private Context context;

    public MyCustomArrayAdapter(List<Person> personList, Context context) {
        super(context, R.layout.layout_custom_adapter, personList);
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = personList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_custom_adapter,
                    parent,
                    false
            );

            viewHolder = new ViewHolder();
            viewHolder.txtVwPersonInfo = (TextView) convertView.findViewById(R.id.txt_vw_person_info);
            viewHolder.imgVwPersonAvatar = (ImageView) convertView.findViewById(R.id.img_vw_person_avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgVwPersonAvatar.setImageResource(R.mipmap.ic_avatar_foreground);
        viewHolder.txtVwPersonInfo.setText(person.toString());

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgVwPersonAvatar;
        TextView txtVwPersonInfo;
    }
}
