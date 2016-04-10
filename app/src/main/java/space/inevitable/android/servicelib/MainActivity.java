package space.inevitable.android.servicelib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import space.inevitable.android.service.EventDrivenServiceConnector;
import space.inevitable.android.service.OnConnectedToServiceEvent;
import space.inevitable.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    EventDrivenServiceConnector< TestService > serviceEventDrivenServiceConnector;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        serviceEventDrivenServiceConnector = new EventDrivenServiceConnector<>( this );
    }

    @Override
    protected void onStart() {
        super.onStart();

        serviceEventDrivenServiceConnector.connect( TestService.class );
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceEventDrivenServiceConnector.disconnect();
    }

    @Subscribe
    public void onConnectedToService( OnConnectedToServiceEvent onConnectedToServiceEvent ) {
        final TestService service = ( TestService ) onConnectedToServiceEvent.getService();
        service.doSomething();
    }

    @Subscribe
    public void react( String data ) {
        Log.d( "AAA", data );
    }

}
