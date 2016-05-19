package mehagarg.android.booksearch.dbTutorial;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import mehagarg.android.booksearch.R;

/**
 * Created by meha on 5/18/16.
 */
public class PostCursorAdapter extends CursorAdapter {

    public PostCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_sample, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
//        TextView tvUserName = (TextView) view.findViewById(R.id.lvBooks);
//        TextView tvUserURL = (TextView) view.findViewById(R.id.lvBooks);

        String post = cursor.getString(cursor.getColumnIndex(""));
        String user = cursor.getString(cursor.getColumnIndex(""));

//        tvUserName.setText(body);

    }
}
