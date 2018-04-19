package com.example.wizte.ik_test_app.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wizte.ik_test_app.callbacks.OnRecyclerItemClick;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.model.PhotoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private OnRecyclerItemClick onRecyclerItemClick;
    private List<PhotoItem> photoItems = new ArrayList<>();

    public void addPhotoItems(List<PhotoItem> photoRecentItems) {
        this.photoItems.addAll(photoRecentItems);
        notifyDataSetChanged();
    }

    public void clearPhotoItems() {
        photoItems.clear();
        notifyDataSetChanged();
    }

    public PhotoAdapter(OnRecyclerItemClick onRecyclerItemClick) {
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_photo_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(photoItems.get(position).getUrlM()).fit().centerCrop().into(holder.photo);
        if (photoItems.get(position).getTitle().isEmpty()) {
            holder.title.setText(R.string.image_untitled);
        } else {
            holder.title.setText(photoItems.get(position).getTitle());
        }
        holder.cardView.setOnClickListener(v -> onRecyclerItemClick.onRecyclerItemClick(v, position));
    }

    @Override
    public int getItemCount() {
        return photoItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_item)
        ImageView photo;

        @BindView(R.id.title_item)
        TextView title;

        @BindView(R.id.card_recycler)
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
