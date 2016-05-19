package meha.garg.com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import meha.garg.com.criminalintent.model.Crime;
import meha.garg.com.criminalintent.model.CrimeLab;

/**
 * Created by meha on 4/28/16.
 */
public class CriminalPagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    private List<Crime> mCrimes;

    private static final String EXTRA_CRIME_ID = "extra_info_Crime";

    public static Intent newIntent(Context context, UUID crimeID) {
        Intent i = new Intent(context, CrimeActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeID);
        return i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
    }
}
