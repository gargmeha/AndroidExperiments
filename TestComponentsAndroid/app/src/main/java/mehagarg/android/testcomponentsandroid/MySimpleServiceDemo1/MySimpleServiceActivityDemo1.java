package mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by meha on 5/16/16.
 */
public class MySimpleServiceActivityDemo1 extends AppCompatActivity {
    private Button button;
    private MySimpleReceiver simpleReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.my_simple_service_activity);



        button = (Button) findViewById(R.id.simpleServiceButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySimpleServiceActivityDemo2.this, MySimpleIntentServiceDemo2.class);
                intent.putExtra("foo", "bar");
                intent.putExtra("receiver", simpleReceiver);
                startService(intent);
            }
        });

//        setUpServiceReceiver();
    }

    private void setUpServiceReceiver() {
        simpleReceiver = new MySimpleReceiver(new Handler());
        simpleReceiver.setReceiver(new MySimpleReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String res = resultData.getString("resultVal");
                    Toast.makeText(MySimpleServiceActivityDemo2.this, res, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
