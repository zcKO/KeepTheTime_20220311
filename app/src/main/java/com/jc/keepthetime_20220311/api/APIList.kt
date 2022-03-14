package com.jc.keepthetime_20220311.api

import com.jc.keepthetime_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    // BASE_URL 에 해당하는 서버에서, 어떤 기능들을 사용할 건지 명시

    // @Field => formData 를 보낸다.
    @FormUrlEncoded                         // 파라미터중, Field (FormData) 에 담아야하는 파라미터가 있다면, 필요한 구문.
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    ): Call<BasicResponse>                  // 서버가 주는 응답을 (성공시에) BasicResponse 형태로 자동 파싱


    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String
    ): Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo(
        @Header("X-Http-Token") token: String,
    ): Call<BasicResponse>

    @GET("/user/check")
    fun getRequestDuplicatedCheck(
        @Query("type") type: String,
        @Query("value") value: String
    ): Call<BasicResponse>

    @GET("/user/friend")
    fun getRequestFriendList(
        @Header("X-Http-Token") token: String,
        @Query("type") type: String, // all, my, requested 세 문구 외에는 넣지 말자.
    ) : Call<BasicResponse>

}