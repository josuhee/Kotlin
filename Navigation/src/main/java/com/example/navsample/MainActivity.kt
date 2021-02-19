package com.example.navsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    val fragment_home:HomeFragment= HomeFragment()
    val fragment1:Test1Fragment= Test1Fragment()
    val fragment2:Test2Fragment= Test2Fragment()
    var home:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_layout_toolbar)
        setFragment()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        main_navigationView.setNavigationItemSelectedListener(this)
    }

    //버튼 누르면 네비게이션 열기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                main_drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //어떤 메뉴가 선택되었는지 검사
    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        main_drawer_layout.closeDrawers()
        when(item.itemId){
            R.id.item1->{
                Log.d("test","item1")
                onFragmentSelected(1)
            }
            R.id.item2->{
                Log.d("test","item2")
                onFragmentSelected(2)
            }
        }
        return false
    }

    //navigation창을 열었을 때 뒤로가기 누를때
    override fun onBackPressed() {
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
        }
        else if(home==1){
            supportFragmentManager.beginTransaction().replace(R.id.home_frame,fragment_home).commit()
            home=0
        }
        else super.onBackPressed()
    }

    //화면 전환
    fun onFragmentSelected(position:Int){
        var curFragment:Fragment
        curFragment=fragment_home
        if(position==1){
            curFragment=fragment1
        }
        else if(position==2){
            curFragment=fragment2
        }
        home=1
        supportFragmentManager.beginTransaction().replace(R.id.home_frame,curFragment).commit()
    }

    //초기화면 set
    fun setFragment(){
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.home_frame,fragment_home)
        transaction.commit()
    }
}