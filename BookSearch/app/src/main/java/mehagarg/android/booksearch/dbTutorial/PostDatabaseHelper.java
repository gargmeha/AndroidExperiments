package mehagarg.android.booksearch.dbTutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mehagarg.android.booksearch.dbTutorial.model.Post;
import mehagarg.android.booksearch.dbTutorial.model.User;

/**
 * Created by meha on 5/18/16.
 */
public class PostDatabaseHelper extends SQLiteOpenHelper {

//    https://guides.codepath.com/android/Local-Databases-with-SQLiteOpenHelper
    //db

    private static PostDatabaseHelper sInstance;
    public static final String dbName = "userpost.db";
    public static final int dbversion = 1;

    //tables
    public static final String TABLE_POSTS = "posts";
    public static final String TABLE_USERS = "users";

    // Columns for POST
    public static final String KEY_POST_ID = "id";
    public static final String KEY_POST_USER_ID_FK = "userId";
    public static final String KEY_POST_TEXT = "text";

    // Columns for USERS
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";


    private PostDatabaseHelper(Context context) {
        super(context, dbName, null, dbversion);
    }

    public static synchronized PostDatabaseHelper getINSTANCE(Context context) {
        if (sInstance == null) {
            sInstance = new PostDatabaseHelper(context);
        }
        return sInstance;
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "create table " + TABLE_POSTS +
                " ( " + KEY_POST_ID + " INTEGER PRIMARY KEY , "
                + KEY_POST_USER_ID_FK  + " INTEGER REFERENCES FOREIGN KEY , "
                + KEY_POST_TEXT + " TEXT )";
//        create table TABLE_POSTS (KEY_POST_ID integer primary key, KEY_POST_USER_ID_FK integer references Table TABLE_USERS, KEY_POST_TEXT text);

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS +
                " ( " + KEY_POST_ID + " INTEGER PRIMARY KEY , "
                + KEY_USER_NAME + " TEXT , "
                + KEY_USER_PROFILE_PICTURE_URL + " TEXT "
                + " ) ";
        db.execSQL(CREATE_POST_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(" drop table if exists " + TABLE_USERS);
            db.execSQL(" drop table if exists " + TABLE_POSTS);
            onCreate(db);
        }
    }

    public void addPost(Post post) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            long userId = addOrUpdateUser(post.user);
            ContentValues values = new ContentValues();
            values.put(KEY_POST_USER_ID_FK, userId);
            values.put(KEY_POST_TEXT, post.text);

            db.insertOrThrow(TABLE_POSTS, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private long addOrUpdateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        long userId = -1;

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_NAME, user.username);
            values.put(KEY_USER_PROFILE_PICTURE_URL, user.profilePic_uri);

            int rows = db.update(TABLE_USERS, values, KEY_USER_NAME + "=?", new String[]{user.username});

            if (rows == 1) {
                String userSelectQuery = String.format("Select %s from %s where %s = ?",
                        KEY_USER_ID, TABLE_USERS, KEY_USER_NAME);
                Cursor cursor = db.rawQuery(userSelectQuery, new String[]{String.valueOf(user.username)});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }

                }
            } else {
                userId = db.insertOrThrow(TABLE_USERS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return userId;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String POSTS_SELECT_QUERY =
                String.format("Select * from %s LEFT outer join %s on %s.%s=%s.%s ",
                        TABLE_POSTS, TABLE_USERS, TABLE_POSTS, KEY_POST_USER_ID_FK,
                        TABLE_USERS, KEY_USER_ID
                        );

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    User newUser = new User();
                    newUser.username = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
                    newUser.profilePic_uri = cursor.getString(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE_URL));

                    Post newPost = new Post();
                    newPost.text = cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT));
                    newPost.user = newUser;
                    posts.add(newPost);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return posts;
    }
}
