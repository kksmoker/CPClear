package cn.bytestream.cpc.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class CPCService extends Service {
	private ClipboardManager clipboardManager = null;

	private OnPrimaryClipChangedListener clipChangedListener = new OnPrimaryClipChangedListener() {

		@Override
		public void onPrimaryClipChanged() {
			ClipboardManager cbManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			if (cbManager.getPrimaryClipDescription().hasMimeType("text/plain")) {
				String clipContent = cbManager.getPrimaryClip().getItemAt(0)
						.getText().toString();
				if (clipContent.startsWith("[cp]")
						&& clipContent.endsWith("[/cp]")) {
					clipContent = clipContent.substring(4,
							clipContent.length() - 5);
					ClipData clipData = ClipData.newPlainText("[cp] filter",
							clipContent);
					cbManager.setPrimaryClip(clipData);
				}
			}
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		clipboardManager.addPrimaryClipChangedListener(clipChangedListener);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if (clipboardManager != null) {
			clipboardManager
					.removePrimaryClipChangedListener(clipChangedListener);
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
