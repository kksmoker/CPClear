package cn.bytestream.cpc.broadcast;

import cn.bytestream.cpc.service.CPCService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CPCBroadcastReceiver extends BroadcastReceiver {
	static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(BOOT_COMPLETED)) {
			Intent cpcService = new Intent(context, CPCService.class);
			context.startService(cpcService);
		}
	}

}
