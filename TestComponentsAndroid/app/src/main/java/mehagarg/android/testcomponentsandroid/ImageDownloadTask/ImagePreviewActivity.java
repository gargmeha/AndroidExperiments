package mehagarg.android.testcomponentsandroid.ImageDownloadTask;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import mehagarg.android.testcomponentsandroid.MySimpleServiceDemo1.R;

//Use case #4: Demo a long download with a 10 second sleep. Close the app, display in Notification Center when done.
//Use case #5: Show the downloaded image in the expansion view within notification.

//https://github.com/codepath/android-services-demo

/**
 * Created by meha on 5/16/16.
 */
public class ImagePreviewActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_image_download_task);
        imageView = (ImageView) findViewById(R.id.imageViewTask);
        Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
        imageView.setImageBitmap(bitmap);

    }
}
