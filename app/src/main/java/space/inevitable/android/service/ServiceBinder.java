package space.inevitable.android.service;

import android.os.Binder;

public class ServiceBinder<ServiceType> extends Binder {
    final ServiceType service;

    public ServiceBinder( final ServiceType service ) {
        this.service = service;
    }

    public ServiceType getService() {
        return service;
    }
}
