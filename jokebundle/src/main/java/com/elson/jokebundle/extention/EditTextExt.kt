package com.elson.jokebundle.extention

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by apple on 2018/3/1.
 */

fun EditText.beforeTextChanged(action: (s: CharSequence?, start: Int, count: Int, after: Int)->Unit) = textWatch(before = action)

fun EditText.onTextChanged(action: (s: CharSequence?, start: Int, count: Int, after: Int)->Unit) = textWatch(change = action)

fun EditText.afterTextChanged(action: (s: Editable?)->Unit) = textWatch(after = action)


fun EditText.textWatch(
        before: ((s: CharSequence?, start: Int, count: Int, after: Int)->Unit)? = null,
        change: ((s: CharSequence?, start: Int, before: Int, count: Int)->Unit)? = null,
        after:  ((s: Editable?) -> Unit)? = null
) : EditText {

    return apply {
        addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                after?.invoke(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before?.invoke(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                change?.invoke(s, start, before, count)
            }

        })
    }

}






