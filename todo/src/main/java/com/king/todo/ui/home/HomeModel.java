package com.king.todo.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.king.todo.R;
import com.king.todo.databinding.ActivityHomeBinding;
import com.king.todo.inject.qualifier.manager.ActivityFragmentManager;

import javax.inject.Inject;

import io.reactivex.Observable;

@ModelView(R.layout.activity_home)
public class HomeModel extends PagerModel<HomeActivity, ActivityHomeBinding, HomeEntity>  {
    @Inject
    HomeModel(@ActivityFragmentManager FragmentManager fragmentManager) {
        super(new FragmentAdapter<>(fragmentManager));
    }

    @Override
    public void attachView(Bundle savedInstanceState, HomeActivity activity) {
        super.attachView(savedInstanceState, activity);
        setRcHttp((offset1, refresh) -> Observable.range(0, 3)
                .map(i -> new HomeEntity())
                .toList()
                .toObservable()
        );
        getDataBinding().bottomNavigation.setOnNavigationItemSelectedListener(this::onTabItemSelected);
        getDataBinding().viewPager.addOnPageChangeListener(this);
    }

    public void onPageSelected(int position){
        switch (position){
            case 0:getDataBinding().bottomNavigation.setSelectedItemId(R.id.one);break;
            case 1:getDataBinding().bottomNavigation.setSelectedItemId(R.id.two);break;
            case 2:getDataBinding().bottomNavigation.setSelectedItemId(R.id.three);break;
            case 3:getDataBinding().bottomNavigation.setSelectedItemId(R.id.four);break;
        }
    }
    private boolean onTabItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.one:getDataBinding().viewPager.setCurrentItem(0);break;
            case R.id.two:getDataBinding().viewPager.setCurrentItem(1);break;
            case R.id.three:getDataBinding().viewPager.setCurrentItem(2);break;
            case R.id.four:getDataBinding().viewPager.setCurrentItem(3);break;
        }
        return true;
    }

}
