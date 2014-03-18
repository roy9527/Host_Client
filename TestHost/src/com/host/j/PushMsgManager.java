package com.host.j;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class PushMsgManager {

	private static PushMsgManager manager = null;

	private Context context;

	private Handler handler;

	private IHostService mService;

	private PushMsgManager(Context context) {
		this.context = context;
		handler = new Handler(context.getMainLooper());
	}

	synchronized public static PushMsgManager getInstance(Context context) {
		if (manager == null) {
			manager = new PushMsgManager(context);
		}
		return manager;
	}

	public void init() {
		checkHost();
	}

	private void checkHost() {
		if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
			File dir = new File(Environment.getExternalStorageDirectory(),
					"host_push");
			// if (!dir.exists()) {
			// dir.mkdirs();
			// launchLink();
			// } else {
			// queryHost();
			// }
		}
		queryHost();
	}

	private void queryHost() {
		Toast.makeText(context, "start bind service", 1500).show();
		Intent i = new Intent("com.host.j.IHostService");
		i.putExtra("package_name", context.getPackageName());
		try {
			boolean bind = context.bindService(i, mConnection,
					Context.BIND_AUTO_CREATE);
			if (bind) {
				Toast.makeText(context,
						context.getPackageName() + " bind service ok", 1500)
						.show();
			} else {
				Toast.makeText(context,
						context.getPackageName() + " bind service error", 1500)
						.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context,
					context.getPackageName() + " bind service exception", 1500)
					.show();
		}
	}

	private ICallBack.Stub mCallBack = new ICallBack.Stub() {

		@Override
		public void invokCallBack(PushMsg msg) throws RemoteException {
			onNotify(msg);
		}
	};

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			try {
				mService = IHostService.Stub.asInterface(service);
				mService.registerCallBack(context.getPackageName(), mCallBack);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName className) {
			mService = null;
		}
	};

	private void onNotify(final PushMsg m) {
		handler.post(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(context,
						context.getPackageName() + " received " + m.getType(),
						1000).show();
			}
		});
	}
}
