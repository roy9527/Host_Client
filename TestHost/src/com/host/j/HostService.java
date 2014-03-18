package com.host.j;

import java.util.HashMap;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.widget.Toast;

public class HostService extends Service {

	private HashMap<String, ICallBack> callbackMap = null;

	private int index = 0;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			PushMsg m = new PushMsg();
			m.setType(index++ + "");
			m.setTitle(getPackageName());
			m.setContent(getPackageName() + " is content");
			m.setAction("http://www.baidu.com");
			if (!callbackMap.isEmpty()) {
				for (String key : callbackMap.keySet()) {
					ICallBack ib = callbackMap.get(key);
					try {
						ib.invokCallBack(m);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			handler.sendEmptyMessageDelayed(111, 10000);
		}
		
	};

	@Override
	public IBinder onBind(Intent intent) {
		Bundle b = intent.getExtras();
		String pn = b.getString("package_name");
		Toast.makeText(HostService.this, pn + " binded", 1500).show();
		return mBinder;
	}

	IHostService.Stub mBinder = new IHostService.Stub() {

		@Override
		public void registerCallBack(String packageName, ICallBack callBack)
				throws RemoteException {
			if (!callbackMap.containsKey(packageName)) {
				callbackMap.put(packageName, callBack);
			}
		}

		@Override
		public void unregisterCallBack(String packageName)
				throws RemoteException {
			if (callbackMap.containsKey(packageName)) {
				callbackMap.remove(packageName);
			}
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		callbackMap = new HashMap<String, ICallBack>();
	}

	@Override
	public void onDestroy() {
		callbackMap.clear();
		handler.removeMessages(111);
		handler.sendMessage(new Message());
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(HostService.this, getPackageName() + " service start",
				1000).show();
		launchLink();
		return super.onStartCommand(intent, flags, startId);
	}

	private void launchLink() {
		handler.sendEmptyMessage(111);
	}
}
