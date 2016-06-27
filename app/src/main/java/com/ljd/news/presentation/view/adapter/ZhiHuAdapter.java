package com.ljd.news.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljd.news.R;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;
import com.ljd.news.presentation.view.component.AutoLoadImageView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ljd.news.utils.Utils.checkNotNull;

public class ZhiHuAdapter extends RecyclerView.Adapter<ZhiHuAdapter.ViewHolder>{

    public interface OnItemClickListener{
        void onUserItemClicked(ZhiHuStoryItemModel zhiHuStoryItemModel);
    }

    private List<ZhiHuStoryItemModel> storyList;
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
        ZhiHuStoryItemModel zhiHuStoryItemModel = storyList.get(position);
        holder.storyImageView.loadUrl(zhiHuStoryItemModel.getImage());
        holder.storyTitleText.setText(zhiHuStoryItemModel.getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (this.onItemClickListener != null){
                onItemClickListener.onUserItemClicked(zhiHuStoryItemModel);
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

    public void setStoryList(Collection<ZhiHuStoryItemModel> storyList) {
        checkNotNull(storyList,"storyList == null");
        this.storyList = (List<ZhiHuStoryItemModel>) storyList;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    final static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.zhi_hu_story_image)
        AutoLoadImageView storyImageView;

        @BindView(R.id.zhi_hu_story_title_text)
        TextView storyTitleText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
