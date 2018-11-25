package com.king.todo.mvvm;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.king.todo.R;
import com.king.todo.databinding.ActivityImageBinding;
import com.king.todo.independent.annotation.LayoutHelper;
import com.king.todo.independent.base.IBaseActivity;
import com.king.todo.inject.component.ActivityComponent;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/2 10:55 PM
 * 只有编译器可能不骗你。
 */
@LayoutHelper(R.layout.activity_image)
@Route(path = ActivityComponent.Router.image)
public class ImageActivity extends IBaseActivity<ActivityImageBinding> {
    private ImageViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                    .get(ImageViewModel.class);
        viewModel.getImage().observe(this,(s -> getDataBinding().image.setText(s.getName())));
    }
    public void onAClick(View view){
        viewModel.changePeople();
    }
    public void onBClick(View view){
        viewModel.setPeopleName();
    }
    //ViewModel和Activity的对应关系是什么，一对一 还是多对一，或者一对多？
    //观察者们应该写在哪，viewmodel不是持久的层吗啊啊啊啊啊X¥
}
