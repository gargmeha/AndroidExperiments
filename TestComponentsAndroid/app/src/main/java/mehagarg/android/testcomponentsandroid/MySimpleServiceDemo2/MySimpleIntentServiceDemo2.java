package mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import mehagarg.android.testcomponentsandroid.MySimpleServiceDemo2.MySimpleServiceActivityDemo2;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MySimpleIntentServiceDemo2 extends IntentService {
    public static final String TAG = MySimpleIntentServiceDemo2.class.getName();
    public static final int NOTI_ID = 56;
    long timestamp;

    public MySimpleIntentServiceDemo2() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            timestamp = System.currentTimeMillis();

            // Extract additional values from the bundle
            String val = intent.getStringExtra("foo");

            sleep();

            createNotification(val, timestamp);

        }
    }

    private void createNotification(String val, long timestamp) {
        Intent i = new Intent(this, MySimpleServiceActivityDemo2.class);
        i.putExtra("message", "Launched via notification with message: " + val + " and timestamp " + timestamp);

        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_menu_search)
                .setContentTitle("New Message")
                .setContentText("Simple Intent service has a new message")
                .setContentIntent(pi)
                .build();
        nm.notify(NOTI_ID, builder);
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
