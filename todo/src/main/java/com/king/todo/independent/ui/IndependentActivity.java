package com.king.todo.independent.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.view.View;

import com.king.todo.R;
import com.king.todo.databinding.ActivityIdependBinding;
import com.king.todo.independent.annotation.LayoutHelper;
import com.king.todo.independent.base.IBaseActivity;
import com.king.todo.independent.base.utils.NotifyManager;
import com.king.todo.independent.popup.TestPopup;
import com.king.todo.independent.recycler.adapter.IAdapter;
import com.king.todo.independent.recycler.entity.IndependentEntity;
import com.king.todo.independent.recycler.manager.XLayoutManager;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.king.todo.ui.MoneyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @name money
 * @class name：com.king.todo.independent.ui
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 10:08 PM
 * @change
 * @chang time
 * @class describe
 */
@LayoutHelper(value = R.layout.activity_idepend)
//@Route(path = ActivityComponent.Router.home)
public class IndependentActivity extends IBaseActivity<ActivityIdependBinding> implements OnDateSetListener {
    private String test ="12222";
    public String getTest() {
        return test;
    }
    public void setTest(String test) {
        this.test = test;
    }
    private IAdapter<IndependentEntity> adapter=new IAdapter<>();
    private XLayoutManager layoutManager=new XLayoutManager();
    private TestPopup popup;
    private NotifyManager notifyManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getDataBinding().recyclerView.setLayoutManager(layoutManager);
//        getDataBinding().recyclerView.setAdapter(adapter);
//        LinearSnapHelper helper = new LinearSnapHelper();
//        helper.attachToRecyclerView(getDataBinding().recyclerView);
//        List<IndependentEntity> list=new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(new IndependentEntity("第："+i));
//        }

//        ItemTouchHelper helper =new ItemTouchHelper(new SideViewHelper());
//        helper.attachToRecyclerView(getDataBinding().recyclerView);
//        adapter.seteList(list);
       /* Disposable subscribe = RxFileViewer.build(this, RxFileViewerFragment.IMAGE).start().subscribe(file -> {
            BaseUtil.toast(this, file.getAbsolutePath());
        });*/
        notifyManager=new NotifyManager(this);
       getDataBinding().setNotify(notifyManager);
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

    }
    public void onSendMsgClick(View view){
        notifyManager.sendNotify();
    }
}
