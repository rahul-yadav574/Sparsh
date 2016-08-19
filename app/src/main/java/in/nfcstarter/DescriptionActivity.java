package in.nfcstarter;

import android.app.DownloadManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import in.nfcstarter.Fragments.About;
import in.nfcstarter.Fragments.Images;
import in.nfcstarter.Fragments.Videos;

public class DescriptionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static String actualQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        String query = getIntent().getStringExtra(LandingActivity.NFC_QUERY);

        switch (query){
            case "refri":
                actualQuery = "Refrigerator";
                break;
            case "lappy":
                actualQuery = "Laptop";
                break;
            case "telev":
                actualQuery = "Television";
                break;
            default:
                actualQuery = null;
        }

     //   Utility.toastL(DescriptionActivity.this,getIntent().getStringExtra(LandingActivity.NFC_QUERY));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(actualQuery);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).setIcon(R.drawable.images);
        tabLayout.getTabAt(0).setIcon(R.drawable.description);
        tabLayout.getTabAt(2).setIcon(R.drawable.video);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new About());
        adapter.addFragment(new Images());
        adapter.addFragment(new Videos());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }


    }

}
