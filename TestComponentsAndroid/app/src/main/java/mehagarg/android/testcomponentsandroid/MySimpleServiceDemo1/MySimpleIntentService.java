package mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.Toast;

//https://github.com/codepath/android-services-demo
//Use case #1: Demo a simple intent service, sleep, then toast using ResultReceiver

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MySimpleIntentService extends IntentService {
    public static final String TAG = mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1.MySimpleIntentServiceDemo2.class.getName();
    long timestamp;

    public MySimpleIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            timestamp = System.currentTimeMillis();

            // Extract additional values from the bundle
            String val = intent.getStringExtra("foo");

            ResultReceiver rec = intent.getParcelableExtra("receiver");
            sleep();
            //send result to the activity
            sendResultValue(rec, val);

        }
    }

    private void sendResultValue(ResultReceiver rec, String val) {
        Bundle bundle = new Bundle();
        bundle.putString("resultVal", "My Result Value. You passed in : " + val + " with timestamp " + timestamp);
        rec.send(Activity.RESULT_OK, bundle);
    }

    private void sleep() {
        try {
            Thread.sleep(20000);
            Toast.makeText(this, "Waky waky Service", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
