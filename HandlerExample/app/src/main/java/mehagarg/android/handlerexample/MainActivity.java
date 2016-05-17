package mehagarg.android.handlerexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    Thread thread, fibThread;
    Handler handler, fibHandler;
    int n = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textViewFib);
        fibThread = new Thread(new MyFibThread(n));
        fibThread.start();

        fibHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String s = String.valueOf(msg.arg1);
                textView.setText(s.toString());
            }
        };




        // logic for updating progress bar
        progressBar = (ProgressBar) findViewById(R.id.pb);
        thread = new Thread(new MyThread());
        thread.start();

        // stuck with mq of the main thread
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
            }
        };



    }


    class MyThread implements Runnable{
        @Override
        public void run() {

            for(int i=0; i<100; i++) {
                Message msg = Message.obtain();
                msg.arg1=i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyFibThread implements Runnable{
        int n;

        public MyFibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            int res = 0;
            for(int j=0; j<=n; j++) {
                res = getFibonacci(j);

                Message msg = Message.obtain();
                msg.arg1 = res;
                fibHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        private int getFibonacci(int n) {
            if(n==0)
                return 0;
            if(n == 1)
                return 1;
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }
}
