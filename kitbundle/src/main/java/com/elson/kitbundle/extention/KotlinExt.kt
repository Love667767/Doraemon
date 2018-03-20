package com.elson.kitbundle.extention

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson

/**
 *
 * Kotlin扩展类
 * Created by apple on 2018/2/27.
 */

/**
 * Gson 相关
 */
inline fun <reified T> Gson.fromJson(json: String) : T = fromJson(json, T::class.java)


fun RecyclerView.setHDirection(): RecyclerView = apply {
    val manager = LinearLayoutManager(context)
    manager.setOrientation(LinearLayoutManager.HORIZONTAL)
    setLayoutManager(manager)
}

fun RecyclerView.setVDirection(): RecyclerView = apply {
    val manager = LinearLayoutManager(context)
    manager.setOrientation(LinearLayoutManager.VERTICAL)
    setLayoutManager(manager)
}

