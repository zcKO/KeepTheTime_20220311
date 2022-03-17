package com.jc.keepthetime_20220311

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.jc.keepthetime_20220311.api.APIList
import com.jc.keepthetime_20220311.api.ServerApi

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    // 모든 화면에서, apiList 변수가 있다면 => apiList."서버기능()" 형태로 간단히 코딩이 가능하다.
    lateinit var apiList: APIList

    // 액션바의 UI 요소들을 멤버 변수로 => 상속 받은 화면들이 각자 컨트롤 가능.
    lateinit var txtTitle: TextView
    lateinit var btnAdd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)

        // 실체가 있을 때만 실행
        supportActionBar?.let {
            setCustomActionBar()
        }

    }

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar() {

        val defaultActionBar = supportActionBar!!
//        defaultActionBar.setDisplayShowCustomEnabled(true) // 아래 코드와 같은 기능
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0, 0)

        // UI 요소들 실제 값 대입
        txtTitle = defaultActionBar.customView.findViewById(R.id.txtTitle)
        btnAdd = defaultActionBar.customView.findViewById(R.id.btnAdd)

    }

}