package com.jc.keepthetime_20220311.datas

// 서바가 알려주는 사용자 정보를 담기 위한 (파싱하기 위한) 클래스
class UserData(
    val id: Int,
    val provider: String,
    val uid: String?,       // 서버가 null 을 주는 경우도 있다. String 이 대부분 내려온다.
    val email: String,
    val readyMinute: Int,
    val nickName: String,
    val profileImage: String
) {

}