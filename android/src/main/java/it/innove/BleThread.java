package it.innove;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

final class BleThread {
    private static final String THREAD_NAME = "BleManagerThread";
    private static final HandlerThread BLE_THREAD;
    private static final Handler BLE_HANDLER;

    static {
        BLE_THREAD = new HandlerThread(THREAD_NAME);
        BLE_THREAD.start();
        BLE_HANDLER = new Handler(BLE_THREAD.getLooper());
    }

    private BleThread() {
    }

    static Handler getHandler() {
        return BLE_HANDLER;
    }

    static void runOnBleThread(Runnable runnable) {
        if (Looper.myLooper() == BLE_HANDLER.getLooper()) {
            runnable.run();
        } else {
            BLE_HANDLER.post(runnable);
        }
    }
}
