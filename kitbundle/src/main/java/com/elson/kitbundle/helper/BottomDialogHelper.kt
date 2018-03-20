package com.elson.kitbundle.helper

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.elson.kitbundle.R
import com.elson.kitbundle.extention.setVDirection
import com.elson.kitbundle.ui.adapter.ChannelAdapter
import com.elson.kitbundle.vo.Channel
import org.jetbrains.anko.find

/**
 * Created by apple on 2018/3/7.
 */

class BottomDialogHelper {


    fun createBottomSheetDialog(view: View): BottomSheetDialog {
        val dialog = BottomSheetDialog(view.context)
        dialog.setContentView(view)
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog
    }

    companion object {
        private fun newInstance() = BottomDialogHelper()

        fun showDefaultBottomDialog(ctx: Context, title: String, datas: List<Channel>, click: (Channel) -> Unit) {
            val view = LayoutInflater.from(ctx).inflate(R.layout.kit_dialog_bottom, null)
            val dialog = newInstance().createBottomSheetDialog(view)
            val tvTitle = view.find<TextView>(R.id.tvTitle)
            val recyclerView = view.find<RecyclerView>(R.id.recyclerView)

            tvTitle.text = title
            recyclerView.setVDirection().let {
                val adapter = ChannelAdapter(datas)
                it.adapter = adapter
                adapter
            }.setOnItemClickListener {
                adapter, view, position ->
                click(adapter.getItem(position) as Channel)
                dialog.dismiss()
            }
            dialog.show()
        }
    }


}
