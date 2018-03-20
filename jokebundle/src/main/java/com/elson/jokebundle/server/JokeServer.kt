package com.elson.jokebundle.server

import android.util.Log
import com.elson.jokebundle.vo.JokeGroup
import com.elson.jokebundle.vo.JokeList
import com.elson.jokebundle.vo.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread
import java.net.URL
/**
 * Created by apple on 2018/2/27.
 */

class JokeServer {


    fun execute(handler: (List<JokeGroup>) -> Unit) {
        val url = "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120"

        async {
            val response = URL(url).readText()
            Log.d("JokeServer", response)
            uiThread {
                val result: Response<JokeList> = Gson().fromJson(response,
                        object : TypeToken<Response<JokeList>>() {}.type)

                handler(result.data.data)
            }
        }


    }
}






