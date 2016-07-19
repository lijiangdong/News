/*
 * Copyright (C) 2016 Li Jiangdong Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ljd.news.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljd.news.R;
import com.ljd.news.presentation.model.QiWenNewsResultModel;
import com.ljd.news.presentation.view.component.AutoLoadImageView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ljd.news.utils.Utils.checkNotNull;

public class QiWenNewsAdapter extends RecyclerView.Adapter<QiWenNewsAdapter.ViewHolder> {

    private List<QiWenNewsResultModel> guoNeiNewsResultList;
    private final LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onUserItemClicked(QiWenNewsResultModel qiWenNewsResultModel);
    }

    @Inject
    public QiWenNewsAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.guoNeiNewsResultList = Collections.EMPTY_LIST;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QiWenNewsResultModel qiWenNewsResultModel = guoNeiNewsResultList.get(position);
        holder.image.loadUrl(qiWenNewsResultModel.getPicUrl());
        holder.timeText.setText(qiWenNewsResultModel.getCtime());
        holder.titleText.setText(qiWenNewsResultModel.getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null){
                onItemClickListener.onUserItemClicked(qiWenNewsResultModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.guoNeiNewsResultList != null)?guoNeiNewsResultList.size() : 0;
    }

    public void setGuoNeiNewsList(Collection<QiWenNewsResultModel> guoNeiNewsResultList) {
        checkNotNull(guoNeiNewsResultList);
        this.guoNeiNewsResultList = (List<QiWenNewsResultModel>) guoNeiNewsResultList;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    final static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_image) AutoLoadImageView image;
        @BindView(R.id.news_title_text) TextView titleText;
        @BindView(R.id.news_time_text) TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
