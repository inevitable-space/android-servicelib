package space.inevitable.android.service;

public class OnConnectedToServiceEvent {
    final EventDrivenService service;

    public OnConnectedToServiceEvent(EventDrivenService service ){
        this.service = service;
    }

    public EventDrivenService getService(){
        return service;
    }
}
