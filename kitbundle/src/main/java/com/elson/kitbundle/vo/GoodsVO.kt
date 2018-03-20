package com.elson.kitbundle.vo

/**
 * Created by apple on 2018/3/7.
 */


data class Goods(
        val message: String,
        val nu: String,
        val ischeck: String,
        val condition: String,
        val com:String,
        val status: String,
        val state: String,
        val data: List<Site>
)

// 物流站点
data class Site(
        val time: String,
        val ftime: String,
        val context: String,
        val location: String
)

data class Channel(
        val code: String,
        val name: String
)
