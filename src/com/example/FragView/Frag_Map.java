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
		
		//��map_Map����ֵ
		map_View=(MapView) view.findViewById(R.id.Map);
		map_View.onCreate(savedInstanceState);	//����Ҫд
		map_Map=map_View.getMap();
		//UI�еĶ�λ��ť
		mUiSettings=map_Map.getUiSettings();
		map_Map.setLocationSource(this);
		mUiSettings.setMyLocationButtonEnabled(true);
		//�����Ƿ�ɴ�����λ����ʾ��λ��
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
	
	
	//���λ	
	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(getContext());
			mLocationOption = new AMapLocationClientOption();
			//���ö�λ����
			mlocationClient.setLocationListener(this);
			//����Ϊ�߾��ȶ�λģʽ
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//����ֻ��λһ��
			mLocationOption.setOnceLocation(true);
			//���ö�λ����
			mlocationClient.setLocationOption(mLocationOption);
			mLocationOption.setInterval(8000);
			// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
			// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����stopLocation()������ȡ����λ����
			// �ڶ�λ�������ں��ʵ��������ڵ���onDestroy()����
			// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������stopLocation()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
			
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

	//��λ�ɹ���ص�����
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		// TODO Auto-generated method stub
		if (mListener != null) {
	         if (aLocation != null
	             && aLocation.getErrorCode() == 0) {
	             mListener.onLocationChanged(aLocation);// ��ʾϵͳС����
	             String loc=aLocation.getCity()+aLocation.getDistrict()+aLocation.getStreet()+aLocation.getStreetNum()+aLocation.getAoiName();
	             Toast.makeText(getContext(), loc, 0).show();
	             /**2016/5/7 23:09
	              * CameraPosition(LatLng target, float zoom, float tilt, float bearing)
	              * target��Ŀ��λ�õ���Ļ���ĵ㾭γ�����ꡣ
	              * zoom��Ŀ�������������ż���
	              * title��Ŀ������������б�ȣ��ԽǶ�Ϊ��λ��
	              * bearing����������ָ��ķ����ԽǶ�Ϊ��λ������������ʱ�뷽����㣬��0 �ȵ�360 �ȡ�
	              */
	             changeCamera(CameraUpdateFactory.newCameraPosition(
	            		 new CameraPosition(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()),
	            				 15, 0, 0)), null);
	             
	         } else {
	             String errText = "��λʧ��," + aLocation.getErrorCode()+ ": " + aLocation.getErrorInfo();
	             Log.e("AmapErr",errText);
	             Toast.makeText(getContext(), errText, 0).show();
	         }
	     }
	}
	
}
