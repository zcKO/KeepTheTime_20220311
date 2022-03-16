package com.jc.keepthetime_20220311

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityViewMapBinding
import com.jc.keepthetime_20220311.datas.AppointmentData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener

class ViewMapActivity : BaseActivity() {

    lateinit var binding: ActivityViewMapBinding

    lateinit var mAppointment: AppointmentData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_map)
        mAppointment = intent.getSerializableExtra("appointment") as AppointmentData
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {


        binding.txtTitle.text = mAppointment.title
        binding.txtPlaceName.text = mAppointment.place

        binding.naverMapView.getMapAsync { naverMap ->

            val startCoord = LatLng(mAppointment.start_latitude, mAppointment.start_longitude)
            val endCoord = LatLng(mAppointment.latitude, mAppointment.longitude)
            val startMarker = Marker()
            val endMarker = Marker()
            val cameraUpdate = CameraUpdate.scrollTo(endCoord)
            naverMap.moveCamera(cameraUpdate)

            startMarker.position = startCoord
            endMarker.position = endCoord

            startMarker.map = naverMap
            endMarker.map = naverMap


            val myODsayService = ODsayService.init(mContext, "+49PY7ooyTk1KYzli+tMi2j8iWiI6WcC4EdkansUJz8")

            myODsayService.requestSearchPubTransPath(
                mAppointment.start_longitude.toString(),
                mAppointment.start_latitude.toString(),
                mAppointment.longitude.toString(),
                mAppointment.latitude.toString(),
                null,
                null,
                null,
                object : OnResultCallbackListener {
                    override fun onSuccess(p0: ODsayData?, p1: API?) {
                        val jsonObj = p0!!.json!!
                        Log.d("길찾기 응답",jsonObj.toString())
                    }

                    override fun onError(p0: Int, p1: String?, p2: API?) {

                    }

                }

            )

        }

    }


}