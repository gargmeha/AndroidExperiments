package mehagarg.android.asyntaskexample;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by meha on 4/21/16.
 */
public class MyTask extends AsyncTask<String, Integer, Boolean> {

    private int contentLength = -1;
    private int counter = 0;
    private int progress = 0;
    Context context;


    public MyTask(Context context) {
        onAttach(context);
    }

    public void onAttach(Context context){
        this.context = context;
    }

    public void onDetach(){
        context = null;

    }
    @Override
    protected void onPreExecute() {
        ((MainActivityForImageDownload)context).showHideProgressBarBeforeDownloading();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean successful = false;
        URL downloadUrl = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;

        try {
            downloadUrl = new URL(params[0]);
            connection = (HttpURLConnection) downloadUrl.openConnection();
            contentLength = connection.getContentLength();
            inputStream = connection.getInputStream();

            file = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).
                    getAbsolutePath() + "/" +
                    Uri.parse(params[0]).getPathSegments()));

            fileOutputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];

            int read = -1;

            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
                counter += read;
                publishProgress(counter);
            }
            successful = true;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return successful;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress = (int) ((double) (values[0] / contentLength)) * 100;
        ((MainActivityForImageDownload)context).updateProgress(progress);

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        ((MainActivityForImageDownload)context).showHideProgressBarBeforeDownloading();
    }
}
