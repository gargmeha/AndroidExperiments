package mehagarg.android.testcomponentsandroid.MySimpleServiceDemo2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1.MySimpleIntentServiceDemo2;
import mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1.R;
//https://github.com/codepath/android-services-demo
//Use case #2: Demo a simple intent service, sleep, then display in Notification Center (first builder example)
/**
 * Created by meha on 5/16/16.
 */
public class MySimpleServiceActivityDemo2 extends AppCompatActivity {
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.my_simple_service_activity_demo2);

        button = (Button) findViewById(R.id.simpleServiceButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySimpleServiceActivityDemo2.this, MySimpleIntentServiceDemo2.class);
                intent.putExtra("foo", "bar");
                startService(intent);
            }
        });

        checkForMessage();
    }

    // Use case #3:
    // Upon clicking on a notification, go to a particular activity and display the notification information
    private void checkForMessage() {
        String message = getIntent().getStringExtra("message");
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }


}
