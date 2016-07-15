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

package com.ljd.news.presentation.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.view.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    protected Navigator navigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.navigator = (Navigator) context;
        }
    }

    @Override
    public void onDetach() {
        this.navigator = null;
        super.onDetach();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    public interface Navigator {
        void navigateToActivity(final Intent intent);
    }

    public interface HandleFloatActionButton{
        void hideFloatActionButton();
        void showFloatActionButton();
    }
}
