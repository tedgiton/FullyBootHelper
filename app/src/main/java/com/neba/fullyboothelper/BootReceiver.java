package com.neba.fullyboothelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())
                && !Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction())) {
            return;
        }

        final PendingResult pending = goAsync();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            try {
                Intent launch = new Intent();
                launch.setClassName(
                        "de.ozerov.fully",
                        "de.ozerov.fully.FullyActivity"
                );

                launch.addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                );

                context.startActivity(launch);

            } finally {
                pending.finish();
            }
      }, 30000);
    }
}
