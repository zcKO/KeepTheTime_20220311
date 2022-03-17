package com.jc.keepthetime_20220311.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "e6b84d1fb1b217f1ff5743dd03ccf787")

    }

}