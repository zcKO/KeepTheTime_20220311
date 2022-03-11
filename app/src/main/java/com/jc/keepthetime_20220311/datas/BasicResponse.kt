package com.jc.keepthetime_20220311.datas

// 서버가 주는 응답의 제일 기본형태인 code, message, data 를 파싱해주는 클래스
// Retrofit 과 연계하면, 파싱이 자동 진행된다.
class BasicResponse(
    val code: Int,
    val message: String,
) {
}