package com.king.todo.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/2 11:05 PM
 * 只有编译器可能不骗你。
 */
public class ImageViewModel extends ViewModel {
    private MutableLiveData<UserEntity> image;
    private int position = 0;
    public ImageViewModel() {
        this.image = new MutableLiveData<>();
        image.setValue(new UserEntity("沙雕"));
    }
    public void changePeople(){
        position++;
        image.setValue(new UserEntity("睿智"));
    }
    public void setPeopleName(){
        image.getValue().setName(image.getValue().getName()+"真的睿智");
    }
    public MutableLiveData<UserEntity> getImage() {
        return image;
    }
}
