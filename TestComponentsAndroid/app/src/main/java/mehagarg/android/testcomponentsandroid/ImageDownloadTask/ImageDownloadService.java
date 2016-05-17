package mehagarg.android.testcomponentsandroid.ImageDownloadTask;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by meha on 5/17/16.
 */
public class ImageDownloadService extends IntentService {

    public static final String TAG = ImageDownloadService.class.getName();
    public ImageDownloadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
