package com.example.FragView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.mapcore.al;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.example.realtext.R;

public class Frag_Map extends Fragment implements LocationSource, AMapLocationListener {
	
	private MapView map_View;
	private AMap map_Map;
	private UiSettings mUiSettings;
	
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_map, null);
		
		//给map_Map对象赋值
		map_View=(MapView) view.findViewById(R.id.Map);
		map_View.onCreate(savedInstanceState);	//必须要写
		map_Map=map_View.getMap();
		//UI中的定位按钮
		mUiSettings=map_Map.getUiSettings();
		map_Map.setLocationSource(this);
		mUiSettings.setMyLocationButtonEnabled(true);
		//设置是否可触发定位并显示定位层
		map_Map.setMyLocationEnabled(true);
		map_Map.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
		return view;
	}

	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		map_View.onResume();
		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		map_View.onResume();
		setUpMapIfNeeded();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		map_View.onSaveInstanceState(outState);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		map_View.onDestroy();
	}
	private void setUpMapIfNeeded() {
		if (map_Map == null) {
			map_Map = ((SupportMapFragment) getFragmentManager()
					.findFragmentById(R.id.Map)).getMap();
		}
	}

	private void changeCamera(CameraUpdate update, CancelableCallback callback){
		map_Map.animateCamera(update, 1000, callback);
	}
	
	
	//激活定位	
	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(getContext());
			mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(this);
			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//设置只定位一次
			mLocationOption.setOnceLocation(true);
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			mLocationOption.setInterval(8000);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			
			mlocationClient.startLocation();
		}
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	//定位成功后回调函数
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		// TODO Auto-generated method stub
		if (mListener != null) {
	         if (aLocation != null
	             && aLocation.getErrorCode() == 0) {
	             mListener.onLocationChanged(aLocation);// 显示系统小蓝点
	             String loc=aLocation.getCity()+aLocation.getDistrict()+aLocation.getStreet()+aLocation.getStreetNum()+aLocation.getAoiName();
	             Toast.makeText(getContext(), loc, 0).show();
	             /**2016/5/7 23:09
	              * CameraPosition(LatLng target, float zoom, float tilt, float bearing)
	              * target：目标位置的屏幕中心点经纬度坐标。
	              * zoom：目标可视区域的缩放级别。
	              * title：目标可视区域的倾斜度，以角度为单位。
	              * bearing：可视区域指向的方向，以角度为单位，从正北向逆时针方向计算，从0 度到360 度。
	              */
	             changeCamera(CameraUpdateFactory.newCameraPosition(
	            		 new CameraPosition(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()),
	            				 15, 0, 0)), null);
	             
	         } else {
	             String errText = "定位失败," + aLocation.getErrorCode()+ ": " + aLocation.getErrorInfo();
	             Log.e("AmapErr",errText);
	             Toast.makeText(getContext(), errText, 0).show();
	         }
	     }
	}
	
}
