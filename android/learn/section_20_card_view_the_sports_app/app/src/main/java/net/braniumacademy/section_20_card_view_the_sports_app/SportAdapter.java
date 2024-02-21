package net.braniumacademy.section_20_card_view_the_sports_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder> {
    private List<Sport> sports;

    public SportAdapter(List<Sport> sports) {
        this.sports = sports;
    }

    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_view_item_layout, parent, false);
        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        Sport sport = sports.get(position);
        holder.tvSportName.setText(sport.getSportName());
        holder.ivSportImg.setImageResource(sport.getSportImage());
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public class SportViewHolder extends RecyclerView.ViewHolder {
        TextView tvSportName;
        ImageView ivSportImg;

        public SportViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSportImg = itemView.findViewById(R.id.iv_sport_img);
            tvSportName = itemView.findViewById(R.id.tv_sport_name);
        }
    }
}
