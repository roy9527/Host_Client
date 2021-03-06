package com.host.testa;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.host.j.ICallBack;
import com.host.j.IHostService;
import com.host.j.PushMsgManager;

public class MainActivity extends Activity {

	private IHostService mService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Bundle args = new Bundle();
//		Intent intent = new Intent(this, IHostService.class);
//		args.putString("test", "TestA");
//		intent.putExtras(args);
//		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//		startService(intent);
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PushMsgManager pmm = PushMsgManager.getInstance(MainActivity.this);
				pmm.init();
			}
		});
	}

	@Override
	protected void onDestroy() {
//		unbindService(mConnection);
		super.onDestroy();
	}
}
