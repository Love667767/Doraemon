package com.elson.kitbundle.server

import com.elson.kitbundle.extention.fromJson
import com.elson.kitbundle.vo.Goods
import com.google.gson.Gson
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by apple on 2018/3/7.
 */
class GoodsQueryServer {

    companion object {
        //3353306343466
        val url: String = "https://www.kuaidi100.com/query?type=%s&postid=%s&id=1&valicode=&temp="
    }

    /**
     *
     * @param type : 快递类型(申通、圆通...)
     * @param postid : 快递单号
     */
    fun queryGoodsById(postid: String, channel: String, success:(Goods)-> Unit, fail: (message: String)-> Unit): Unit {
        async {
            val formatUrl = url.format(channel, postid)
            val response = URL(formatUrl).readText()
            val result = Gson().fromJson<Goods>(response)
            uiThread {
                if ("200" == result.status) {
                    success(result)
                } else {
                    fail(result.message)
                }
            }
        }

    }
}