package com.elson.commonbundle;

import android.support.v4.widget.SwipeRefreshLayout;

import com.elson.basecore.base.BaseFragment;
import com.elson.basecore.base.BasePresenter;


/**
 * 列表刷新基类
 * Created by elson on 2017/6/5
 */

public abstract class BasePullToRefreshFragment<T, P extends BasePresenter> extends BaseFragment<P>
        implements SwipeRefreshLayout.OnRefreshListener {

//    @BindView(swipeRefreshLayout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    @BindView(R.id.rv_list)
//    RecyclerView mRecyclerView;
//
//    private BaseQuickAdapter mAdapter;
//
//    protected static final int DEFAULT_NO = 0;
//    /**
//     * 当前加载的页数
//     */
//    private int mPageNo = DEFAULT_NO;
//    /**
//     * 总条数
//     */
//    private int mCount;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_pull_to_refresh;
//    }
//
//    @Override
//    protected void initViewsAndEvents() {
//        mAdapter = getAdapter();
//        if (mAdapter == null) {
//            throw new NullPointerException("Adapter is not null");
//        }
//        SuperRecycleViewUtil.initSwipeLayout(mSwipeRefreshLayout, this);
//        SuperRecycleViewUtil.init(mRecyclerView, mContext);
//        if (isAddItemDecoration()) {
//            mRecyclerView.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL));
//        }
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mRecyclerView.setAdapter(mAdapter);
//
//        mAdapter.setOnLoadMoreListener(() -> {
//            mSwipeRefreshLayout.setEnabled(false);
//            mPageNo++;
//            executeRequest(false);
//            LogUtil.d(TAG, "setOnLoadMoreListener__PageNo：" + mPageNo);
//        }, mRecyclerView);
//
//        initViews();
//        // TODO 请求数据
//        if (isAutoRequest()) {
//            executeRequest(isLoading());
//        }
//    }
//
//    /**
//     * 加分割线
//     *
//     * @return
//     */
//    protected boolean isAddItemDecoration() {
//        return false;
//    }
//
//    protected boolean isLoading() {
//        return true;
//    }
//
//    protected void initViews() {
//
//    }
//
//    protected boolean isAutoRequest() {
//        return true;
//    }
//
//    protected void executeRequest(boolean isShowLoading) {
//        executeRequest(isShowLoading, mPageNo);
//    }
//
//    protected void executeRequest(final boolean isShowLoading, int pageNo) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.putAll(getParams());
//        map.put("pageSize", getPageCount());
//        map.put("pageNo", pageNo < Constant.PAGE_NO_DEFAULT ? DEFAULT_NO : pageNo);
//        LogUtil.d(TAG, "executeRequest__PageNo:" + pageNo);
//        HttpUtils.getInstance().httpPost(getApiUrl(), map, new ResponseCallBack<List<T>>(this, isShowLoading, true, false) {
//
//            @Override
//            public void onSuccess(IResponseResult<List<T>> result) {
//                mSwipeRefreshLayout.setRefreshing(false);
//                mSwipeRefreshLayout.setEnabled(true);
//                mAdapter.loadMoreComplete();
//                if (result == null)
//                    return;
//                mCount = result.getCount();
//
//                if (getEmptyView() != null) {
//                    mAdapter.setEmptyView(getEmptyView());
//                }
//                if (mPageNo == Constant.PAGE_NO_DEFAULT) {
//                    if (isMultipleItem())// Item是多类型的,需要转换成对应的样式
//                        mAdapter.setNewData(changeItemType(result.getResponseBody()));
//                    else
//                        mAdapter.setNewData(result.getResponseBody());
//                } else {
//                    if (isMultipleItem())
//                        mAdapter.addData(changeItemType(result.getResponseBody()));
//                    else
//                        mAdapter.addData(result.getResponseBody());
//                }
//
//                if (mCount <= mAdapter.getData().size()) {
//                    mAdapter.loadMoreEnd();
//                    mAdapter.setEnableLoadMore(false);
//                }
//
//                onCompleted();
//            }
//
//            @Override
//            public void onFailure(String errorMsg) {
//                mSwipeRefreshLayout.setRefreshing(false);
//                mSwipeRefreshLayout.setEnabled(true);
//                mAdapter.setEnableLoadMore(true);
//                mAdapter.loadMoreFail();
//                mPageNo--;
//
//                if (getEmptyView() != null) {
//                    mAdapter.setEmptyView(getEmptyView());
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//                mSwipeRefreshLayout.setRefreshing(false);
//                mSwipeRefreshLayout.setEnabled(true);
//                mAdapter.setEnableLoadMore(true);
//                mAdapter.loadMoreFail();
//                mPageNo--;
//
//                if (getNetErrorView() != null) {
//                    mAdapter.setEmptyView(getNetErrorView());
//                }
//            }
//
//        }, getDataType());
//    }
//
//    @Override
//    public void onRefresh() {
//        mAdapter.setEnableLoadMore(false);
//        resetPageNo();
//        executeRequest(false);
//    }
//
//    protected void resetPageNo() {
//        mPageNo = DEFAULT_NO;
//    }
//
//    protected RecyclerView getRecyclerView() {
//        return mRecyclerView;
//    }
//
//    protected int getPageCount() {
//        return Constant.PAGE_COUNT;
//    }
//
//    protected void onCompleted() {
//        // do something
//    }
//
//    protected void setSwipeRefreshLayoutEnable(boolean isEnable) {
//        mSwipeRefreshLayout.setEnabled(isEnable);
//    }
//
//    protected void setLoadMoreEnable(boolean isEnable) {
//        mAdapter.setEnableLoadMore(isEnable);
//    }
//
//    protected void setRefreshing(boolean isRefreshing) {
//        mSwipeRefreshLayout.setRefreshing(isRefreshing);
//    }
//
//    protected abstract Map<? extends String, ?> getParams();
//
//    protected abstract BaseQuickAdapter getAdapter();
//
//    /**
//     * 获取解析实体类型
//     *
//     * @return
//     */
//    protected abstract Type getDataType();
//
//    /*api url*/
//    protected abstract String getApiUrl();
//
//    /**
//     * 空页面
//     *
//     * @return
//     */
//    protected View getEmptyView() {
//        return null;
//    }
//
//    /**
//     * 网络异常页面
//     *
//     * @return
//     */
//    protected View getNetErrorView() {
//        return EmptyViewHelper.create(mContext,
//                "您的网络不太给力",
//                "点击重试", (v) -> {
//                    executeRequest(true);
//                });
//    }
//
//
//    protected List changeItemType(List<T> detail) {
//        return null;
//    }
//
//    /**
//     * isMultipleItem返回true,，则要重写changeItemType()方法
//     *
//     * @return
//     */
//    protected boolean isMultipleItem() {
//        return false;
//    }
}
