package com.jc.keepthetime_20220311.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerApi {

    // Retrofit 클래스 객체화 함수 => 단일 객체만 만들어서, 모든 화면에서 공유하는 구조.
    // 여러 개의 객체를 만들 필요가 없다. SingleTon 패턴

    companion object {

        // 서버 통신을 담당하는 클래스 ; Retrofit 클래스 객체를 담을 변수
        // 아직 안만들었다면 새로 만들고, 만들어둔게 있다면 있는 retrofit 재활용
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://keepthetime.xyz"

        fun getRetrofit(): Retrofit {

            // Retrofit 라이브러리는, 클래스 차원에서 BASE_URL 을 설정할 수 있게 도와준다.
            // Retrofit + Gson 두 개의 라이브러리르 결합하면 => JSON 파싱이 쉬워진다.

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)      // 어느 서버를 기반으로 움직일 것인지 설정.
                    .addConverterFactory(GsonConverterFactory.create())     // gson 라이브러리와 결합
                    .build()
            }

            return retrofit!!

        }

    }

}