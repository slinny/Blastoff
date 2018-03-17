package productions.darthplagueis.capstone.controller;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 *
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public FragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addOnBoardingFragments(AbstractOnBoardingFragment abstractOnBoardingFragment, String title) {
        fragmentList.add(abstractOnBoardingFragment);
        fragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
