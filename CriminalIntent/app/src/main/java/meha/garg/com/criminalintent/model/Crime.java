package meha.garg.com.criminalintent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by meha on 1/16/16.
 */
public class Crime  implements Parcelable{
    private String mTitle;
    private UUID mId;
    private Date mDate;
    private boolean isSolved;

    public Crime() {
        // generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    protected Crime(Parcel in) {
        mTitle = in.readString();
        isSolved = in.readByte() != 0;
    }

    public static final Creator<Crime> CREATOR = new Creator<Crime>() {
        @Override
        public Crime createFromParcel(Parcel in) {
            return new Crime(in);
        }

        @Override
        public Crime[] newArray(int size) {
            return new Crime[size];
        }
    };

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm//dd//yyyy");
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeByte((byte) (isSolved ? 1 : 0));
    }
}
