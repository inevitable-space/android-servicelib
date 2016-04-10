package space.inevitable.android.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class EventDrivenServiceConnector< ServiceType extends EventDrivenService > implements ServiceConnection {
    ServiceType service;
    Context serviceClient;

    public EventDrivenServiceConnector( Context serviceClient ) {
        this.serviceClient = serviceClient;
    }

    @Override
    public void onServiceConnected( ComponentName name, IBinder iBinder ) {
        ServiceBinder< ServiceType > binder = ( ServiceBinder< ServiceType > ) iBinder;

        service = binder.getService();
        service.register( serviceClient );

        final OnConnectedToServiceEvent serviceTypeOnConnectedToServiceEvent;
        serviceTypeOnConnectedToServiceEvent = new OnConnectedToServiceEvent( service );
        service.post( serviceTypeOnConnectedToServiceEvent  );
    }

    @Override
    public void onServiceDisconnected( ComponentName name ) {
        service.unregister( serviceClient );
        serviceClient.unbindService( this );
    }

    public void disconnect(){
        serviceClient.unbindService( this );
    }
    
    public void connect(Class<ServiceType> serviceTypeClass) {
        Intent intent = new Intent(serviceClient, serviceTypeClass );
        serviceClient.bindService( intent, this, Context.BIND_AUTO_CREATE );
    }
}
