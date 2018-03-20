package com.elson.kitbundle.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.elson.kitbundle.R
import com.elson.kitbundle.extention.setVDirection
import com.elson.kitbundle.helper.BottomDialogHelper
import com.elson.kitbundle.server.GoodsQueryServer
import com.elson.kitbundle.ui.adapter.GoodsAdapter
import com.elson.kitbundle.vo.Channel
import kotlinx.android.synthetic.main.kit_activity_goods_query.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * 快递查询
 * Created by elson on 2018/3/7.
 */
@Route(path = "/kit/goodsQuery")
class GoodsQueryActivity : AppCompatActivity(), AnkoLogger {


    private val mGoodsServer by lazy {
        GoodsQueryServer()
    }

    private val mAdapter by lazy {
        GoodsAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kit_activity_goods_query)

        initToolBar()

        rvRecord.setVDirection().apply {
            this.adapter = mAdapter
        }

        edtPostId.setText("3353306343466")


        tvSearch.setOnClickListener {
            val postId = edtPostId.text.toString().trim()
            val channel = tvChannel.tag as String
            mGoodsServer.queryGoodsById(postId, channel, {
                goods ->
                mAdapter.setNewData(goods.data)
            }, {
                s ->
                toast(s)
            })
        }

        tvChannel.setOnClickListener {
            val datas = listOf<Channel>(
                    Channel("shentong", "申通"),
                    Channel("yuantong", "圆通"),
                    Channel("tiantian", "天天")
            )
            BottomDialogHelper.showDefaultBottomDialog(this, "送货渠道", datas) {
                channel ->
                tvChannel.text = channel.name
                tvChannel.tag = channel.code
            }
        }
    }

    private fun initToolBar() {
        setSupportActionBar(toolBar)
        // 显示返回的箭头
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener { finish() }
    }
}
