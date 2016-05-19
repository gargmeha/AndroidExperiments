package mehagarg.android.booksearch.dbTutorial;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import java.util.List;

import mehagarg.android.booksearch.R;
import mehagarg.android.booksearch.dbTutorial.model.Post;
import mehagarg.android.booksearch.dbTutorial.model.User;

/**
 * Created by meha on 5/18/16.
 */
public class SimpeActivity extends Activity {
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_sample);

        Post samplePost = new Post();
//        sampelUser.username = "Steph";
//        sampelUser.profilePic_uri = "www.goog.com";

        PostDatabaseHelper dbHelper = PostDatabaseHelper.getINSTANCE(this);
        dbHelper.addPost(samplePost);

        // get all posts from db
        List<Post> posts = dbHelper.getAllPosts();
        for (Post post : posts) {

        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Selecte * from Table_POST", null);

        listView = (ListView) findViewById(R.id.lvBooks);
        PostCursorAdapter cursorAdapter = new PostCursorAdapter(this, cursor);
    }
}
