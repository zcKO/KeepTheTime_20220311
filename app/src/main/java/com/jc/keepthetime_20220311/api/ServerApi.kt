package com.jc.keepthetime_20220311.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.jc.keepthetime_20220311.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ServerApi {

    // Retrofit 클래스 객체화 함수 => 단일 객체만 만들어서, 모든 화면에서 공유하는 구조.
    // 여러 개의 객체를 만들 필요가 없다. SingleTon 패턴

    companion object {

        // 서버 통신을 담당하는 클래스 ; Retrofit 클래스 객체를 담을 변수
        // 아직 안만들었다면 새로 만들고, 만들어둔게 있다면 있는 retrofit 재활용
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://keepthetime.xyz"

        fun getRetrofit(context: Context): Retrofit {

            // Retrofit 라이브러리는, 클래스 차원에서 BASE_URL 을 설정할 수 있게 도와준다.
            // Retrofit + Gson 두 개의 라이브러리르 결합하면 => JSON 파싱이 쉬워진다.
            if (retrofit == null) {

                // retrofit 객체 생성시에, 추가 세팅
                // 자동으로 토큰을 첨부하도록
                // retrofit 변수를 통해서 API 통신을 시작하기 직전에, 통신 정보를 먼저 가로챈다.
                // 가로챈 통신 정보에서, 무조건 헤더에 토큰을 첨부해두고, 나머지 작업을 이어가도록.

                val interceptor = Interceptor {
                    with(it) {
                        // 기존의 request 에, 헤더를 추가해주자.
                        val newRequest = request()
                            .newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginUserToken(context))
                            .build()

                        // 다시 하려던 일을 이어가도록한다.
                        proceed(newRequest)
                    }
                }

                // 만들어낸 인터셉터를 활용하도록 세팅.
                // 레트로핏이 사용하는 클라이언트 객체를 수정한다.
                val myClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                // Date 자료형으로 파싱 => String 을 yyyy-MM-dd HH:mm:ss 으로 파싱해서 저장해야한다. (고정된 양식으로 내려줌)
                val gson = GsonBuilder()
                    .setDateFormat("yyy-MM-dd HH:mm:ss")    // 서버가 이런 양식으로 보내주는 String 을
                    .registerTypeAdapter(
                        Date::class.java,
                        DateDeserializer()
                    )     // 어떤 형태의 자료형에 적용시킬지, Date 클래스로 파싱
                    .create()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)      // 어느 서버를 기반으로 움직일 것인지 설정.
                    .addConverterFactory(GsonConverterFactory.create(gson))     // gson 라이브러리와 결합 + Date 파싱 요령 첨부
                    .client(myClient)       // 인터셉터를 부착해둔 클라이언트로 통신하도록 설정.
                    .build()
            }

            return retrofit!!

        }

    }

}