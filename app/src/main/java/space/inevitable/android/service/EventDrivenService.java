package space.inevitable.android.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

import space.inevitable.eventbus.EventBus;
import space.inevitable.eventbus.StandardEventBusBuilder;
import space.inevitable.patterns.Builder;

public class EventDrivenService extends Service {
    private final Random mGenerator = new Random();
    private final EventBus eventBus;

    public EventDrivenService() {
        Builder< EventBus > builder = new StandardEventBusBuilder();
        eventBus = builder.build();
    }

    @Override
    public IBinder onBind( Intent intent ) {
        return new ServiceBinder< EventDrivenService >( this );
    }

    public void register( Object listener ) {
        eventBus.register( listener );
    }

    public void unregister( Object listener ) {
        eventBus.unregister( listener );
    }

    protected void post(Object event){
        eventBus.post( event );
    }
}

