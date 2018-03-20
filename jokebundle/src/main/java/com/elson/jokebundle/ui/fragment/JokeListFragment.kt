package com.elson.jokebundle.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.InterceptorService
import com.alibaba.android.arouter.launcher.ARouter
import com.elson.jokebundle.R
import com.elson.jokebundle.extention.act
import com.elson.jokebundle.server.JokeServer
import com.elson.jokebundle.vo.Joke
import kotlinx.android.synthetic.main.joke_fragment_joke_list.*
import org.jetbrains.anko.AnkoLogger

/**
 * 段子列表页
 * Created by apple on 2018/2/27.
 */


@Route(path = "/joke/jokelist")
class JokeListFragment : Fragment(), AnkoLogger {

    @Autowired
    @JvmField var name: String?=null
    @Autowired
    @JvmField var age:Int? = null
    @Autowired(name = "girl") // 通过name来映射URL中的不同参数
    @JvmField var boy : Boolean? = null

    @Autowired
    @JvmField var joke: Joke? = null    // 支持解析自定义对象，URL中使用json传递

    @Autowired
    @JvmField var inter : InterceptorService? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.joke_fragment_joke_list, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act = (act as AppCompatActivity)
        act.setSupportActionBar(toolBar)
        act.supportActionBar?.setDisplayShowTitleEnabled(true)
        act.supportActionBar?.setDisplayShowHomeEnabled(true)
        //返回按钮
//        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //NavigationIcon点击事件
        toolBar.setNavigationOnClickListener {

        }


        tvLaunch.setOnClickListener {
//            Log.d(this.javaClass.simpleName, "Launch start...")
//            launch {
//                delay(1000L)
//                Log.d(this.javaClass.simpleName, "Launch body")
//            }
//            Log.d(this.javaClass.simpleName, "Launch end")
//            Thread.sleep(2000L) // 阻塞主线程 2 秒，保活


        }




        tvThread.setOnClickListener {

//            Log.d(this.javaClass.simpleName, "Thread start...")
//            thread {
//                Thread.sleep(1000L)
//                Log.d(this.javaClass.simpleName, "Thread body")
//            }
//            Log.d(this.javaClass.simpleName, "Thread end")

//            println("test start")
//            test()
//            println("test end")
        }



        toolBar.setOnClickListener {

            toolBar.title = "段子"
            toolBar.subtitle = "子标题"

        }


        JokeServer().execute {
            response ->
            Log.d("Joke", "回调  ${response.size}")
        }

    }


//    fun test() = runBlocking {
//        val result = withTimeoutOrNull(1000L) {
//            repeat(100) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        }
//
//        val job = launch {
//            repeat(100) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        }
//        println("start...")
//        delay(1300L) // 延迟后就退出
//        println("cancel ...")
//        job.cancel()
//
//        println("join ...")
//        job.join()
//        println("end...")
//    }


//    fun test1() = runBlocking<Unit> {
//        val job = launch {
//            try {
//                repeat(1000) { i ->
//                    println("I'm sleeping $i ...")
//                    delay(500L)
//                }
//            } finally {
//                // 里面是一个整体，不可停止的代码块片段
//                withContext(NonCancellable) {
//                    println("I'm running finally")
//                    delay(1000L)
//                    println("And I've just delayed for 1 sec because I'm non-cancellable")
//                }
//            }
//        }
//        delay(1300L) // delay a bit
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // cancels the job and waits for its completion
//        println("main: Now I can quit.")
//    }


}







