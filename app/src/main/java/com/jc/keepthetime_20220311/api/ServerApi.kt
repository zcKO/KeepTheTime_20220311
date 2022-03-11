package com.jc.keepthetime_20220311.api

import retrofit2.Retrofit

class ServerApi {

    // Retrofit 클래스 객체화 함수 => 단일 객체만 만들어서, 모든 화면에서 공유하는 구조.
    // 여러 개의 객체를 만들 필요가 없다. SingleTon 패턴

    companion object {

        // 서버 통신을 담당하는 클래스 ; Retrofit 클래스 객체를 담을 변수
        // 아직 안만들었다면 새로 만들고, 만들어둔게 있다면 있는 retrofit 재활용
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .build()
            }

            return retrofit!!

        }

    }

}