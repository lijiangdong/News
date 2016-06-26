package com.ljd.news.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljd.news.R;
import com.ljd.news.presentation.model.StoryItemModel;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ljd.news.utils.Utils.checkNotNull;

public class ZhiHuAdapter extends RecyclerView.Adapter<ZhiHuAdapter.ViewHolder>{

    public interface OnItemClickListener{
        void onUserItemClicked(StoryItemModel storyItemModel);
    }

    private List<StoryItemModel> storyList;
    private final LayoutInflater layoutInflater;
    private Context context;

    private OnItemClickListener onItemClickListener;

    @Inject
    public ZhiHuAdapter(Context context) {
        checkNotNull(context,"Context == null");
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        storyList = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StoryItemModel storyItemModel = storyList.get(position);
        Picasso.with(context).load(storyItemModel.getImages().get(0)).into(holder.storyImageView);
        holder.storyTitleText.setText(storyItemModel.getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (this.onItemClickListener != null){
                onItemClickListener.onUserItemClicked(storyItemModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.storyList != null)?storyList.size():0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setStoryList(List<StoryItemModel> storyList) {
        checkNotNull(storyList,"storyList == null");
        this.storyList = storyList;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.zhi_hu_story_image)
        ImageView storyImageView;

        @BindView(R.id.zhi_hu_story_title_text)
        TextView storyTitleText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
