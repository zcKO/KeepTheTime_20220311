package com.jc.keepthetime_20220311

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityEditAppointmentBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

    // 약속 시간 일/시 를 저장해줄 Calendar (월 값이 0 ~ 11 로 움직이게 맞춰져있다.)
    val mSelectedAppointmentDateTime = Calendar.getInstance() // 기본 값: 현재 일시

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        // 날짜 선택 텍스트뷰 클릭 이벤트 - DatePickerDialog
        binding.txtDate.setOnClickListener {
            val dsl = object : DatePickerDialog.OnDateSetListener {

                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    // 연/월/일은, JAVA / Kotlin 언어의 기준 (0 ~ 11월)으로 월 값을 준다. (사람은 1 ~ 12월)
                    // 주는 그대로 Calendar 에 set 하게 되면, 올바른 월로 세팅된다.
                    mSelectedAppointmentDateTime.set(year, month, dayOfMonth)   // 연월일 한번에 세팅하는 함수

                    // 약속 일자의 문구를 22/03/08 등의 형식으로 바꿔서 보여주자.
                    // SimpleDateFormat 을 활용 => 월 값도 알아서 보정
                    val sdf = SimpleDateFormat("yy/MM/dd")

                    binding.txtDate.text = sdf.format(mSelectedAppointmentDateTime.time)

                }
            }

            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedAppointmentDateTime.get(Calendar.YEAR),
                mSelectedAppointmentDateTime.get(Calendar.MONTH),
                mSelectedAppointmentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // 시간 선택 텍스트뷰 클릭 이벤트 - TimePickerDialog
        binding.txtTime.setOnClickListener {
            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
                    // 약속 일시의 시간으로 설정.
                    mSelectedAppointmentDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    mSelectedAppointmentDateTime.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("a h시 m분")
                    binding.txtTime.text = sdf.format(mSelectedAppointmentDateTime.time)
                }

            }

            val tpd = TimePickerDialog(
                mContext,
                tsl,
                18,
                0,
                false
            ).show()

        }

    }

    override fun setValues() {

        // 네이버 지도 객체 얻어오기 => 얻어와지면 할 일 (Interface) 코딩
        binding.naverMapView.getMapAsync {

            // 지도 로딩이 끝나고 난 후에 얻어낸 온전한 지도 객체
            val naverMap = it

            // 지도 시작지점 : 학원 위, 경도
            val coord = LatLng(37.577916, 127.033583)

            // coord 에 설정한 좌표로 > 네이버지도의 카메라 이동
            val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

        }

    }

}