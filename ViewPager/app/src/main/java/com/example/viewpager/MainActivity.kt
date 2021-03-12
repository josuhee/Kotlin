package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currentPosition=0

    //핸들러 설정
    //ui 변경하기
    val handler=Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //어댑터 연결하기
        val adapter=ViewPagerAdapter()
        pager.adapter=adapter

        //버튼 클릭시 2page로
        button.setOnClickListener {
            currentPosition=2
            pager.setCurrentItem(2, true)
        }

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()
    }

    //페이지 변경하기
    fun setPage(){
        if(currentPosition==5) currentPosition=0
        pager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}