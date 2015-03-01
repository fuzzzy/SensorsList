package coffee.code.utils.sensorslist;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    TextView sensorsCount;
    TextView sensorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                sensorsCount = (TextView) findViewById(R.id.total_sens);
                sensorsList = (TextView) findViewById(R.id.list_sens);

                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

                List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
                sensorsCount.setText(sensorList.size()+" sensors!");

                StringBuilder sensorsStr = new StringBuilder();

                for (int i = 0; i < sensorList.size(); i++){
                    Sensor tmp = sensorList.get(i);
                    sensorsStr.append(tmp.getVendor()).append(": ").append(tmp.getName()).append("; \n");
                }

                sensorsList.setText(sensorsStr);
            }
        });
    }
}
