package com.elson.jokebundle.extention

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.toast

/**
 *
 * Kotlin扩展类
 * Created by apple on 2018/2/27.
 */

/**
 * 扩展 V4 包里面的 Fragment
 */
val Fragment.ctx: Context get() = activity

val Fragment.act: Activity get() = activity

fun Context.toast(textResource: Int) = Toast.makeText(this, textResource, Toast.LENGTH_SHORT).show()
inline fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()


inline fun AnkoContext<*>.toast(text: CharSequence) = ctx.toast(text)
inline fun Fragment.toast(text: CharSequence): Toast = activity.toast(text)


/**
 * Gson 相关
 */
inline fun <reified T> Gson.fromJson(json: String) : T = fromJson(json, T::class.java)



