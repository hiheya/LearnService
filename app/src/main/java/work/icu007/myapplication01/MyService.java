package work.icu007.myapplication01;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private boolean serviceRunning = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("lcr","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("lcr","Service Create");

        serviceRunning = true;

        new Thread(){
            @Override
            public void run() {
                while (serviceRunning){
                    Log.d("lcr","服务正在运行");

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serviceRunning = false;
        Log.d("lcr","Service Destroy");
    }
}