package work.icu007.myapplication01;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import work.icu007.myapplication01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(MainActivity.this,MyService.class);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Toast.makeText(this, "Service Connected", Toast.LENGTH_SHORT).show();
        Log.d("lcr","Service connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Toast.makeText(this, "Service disConnected", Toast.LENGTH_SHORT).show();
    }
}