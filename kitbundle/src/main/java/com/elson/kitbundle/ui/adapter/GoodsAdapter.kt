package com.elson.kitbundle.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elson.kitbundle.R
import com.elson.kitbundle.vo.Site

/**
 * Created by apple on 2018/3/7.
 */

class GoodsAdapter(layoutId: Int = R.layout.kit_adapter_goods)
    : BaseQuickAdapter<Site, BaseViewHolder>(layoutId) {


    override fun convert(helper: BaseViewHolder?, item: Site?) {

        with(item!!) {
            helper!!.setText(R.id.tvContent, context)
        }

    }

}
