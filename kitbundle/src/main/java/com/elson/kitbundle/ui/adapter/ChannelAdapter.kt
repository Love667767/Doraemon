package com.elson.kitbundle.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elson.kitbundle.vo.Channel

/**
 * 快递渠道的选择
 * Created by apple on 2018/3/7.
 */
class ChannelAdapter : BaseQuickAdapter<Channel, BaseViewHolder> {


    constructor(datas: List<Channel>, layoutId: Int = android.R.layout.simple_list_item_1) : super(layoutId, datas) {

    }


    override fun convert(helper: BaseViewHolder?, item: Channel?) {
        with(helper!!) {
            setText(android.R.id.text1, item?.name)
        }
    }

}