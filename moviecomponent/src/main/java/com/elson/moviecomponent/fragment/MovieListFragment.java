package com.elson.moviecomponent.fragment;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.elson.basecore.base.BaseFragment;
import com.elson.basecore.base.BasePresenter;
import com.elson.moviecomponent.R;

/**
 * Created by apple on 2018/1/20.
 */
@Route(path = "/movie/movielist")
public class MovieListFragment extends BaseFragment {

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.movie_fragment_movie_list;
    }

    @Override
    protected void initViews() {

    }
}
