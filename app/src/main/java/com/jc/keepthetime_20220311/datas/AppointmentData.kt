package com.jc.keepthetime_20220311.datas

class AppointmentData(
    val id: Int,
    val user_id: Int,
    val title: String,
    val datetime: String,           // 서버는 String 으로 내려주지만, 파싱은 Date 로 바꿔주고 싶다.
    val start_place: String,
    val start_latitude: Double,
    val start_longitude: Double,
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val created_at: String,
    val user: UserData,
    val invited_friends: List<UserData>
)