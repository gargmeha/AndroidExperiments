package meha.garg.com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "extra_info_Crime";

    public static Intent newIntent(Context context, UUID crimeID) {
        Intent i = new Intent(context, CrimeActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeID);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }

}
