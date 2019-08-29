package com.hfad.odometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class OdometerService extends Service {
    private final IBinder binder = new OdometerBinder();
    private final Random random = new Random();

    public OdometerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Double getDistance() {
        return random.nextDouble();
    }

    public class OdometerBinder extends Binder {
        OdometerService getOdometer() {
            return OdometerService.this;
        }
    }
}
