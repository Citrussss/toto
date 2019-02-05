package com.union.bangbang.zero.util.photo;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.io.File;
import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

/**
 * @name zero
 * @anthor bangbang QQ:740090077
 * @time 2018/10/31 8:44 PM
 * 只有编译器可能不骗你。
 */
public class RxFileViewer {
    private WeakReference<RxFileViewerFragment> weakReference;
    private Consumer<File> consumer;
    private @RxFileViewerFragment.FileType String type;
    public Observable<File> start() {
        return Observable.create(emitter -> weakReference.get().selectFile(emitter).openFileManager(type));
    }

    private RxFileViewerFragment getAvoidOnResultFragment(FragmentActivity activity) {
        RxFileViewerFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new RxFileViewerFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private RxFileViewerFragment findAvoidOnResultFragment(FragmentActivity activity) {
        return (RxFileViewerFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }
    public static RxFileViewer build(FragmentActivity activity, @RxFileViewerFragment.FileType String type){
        return new RxFileViewer(activity, type);
    }
    private RxFileViewer(FragmentActivity activity, @RxFileViewerFragment.FileType String type) {
//        this.consumer = consumer;
        this.type = type;
        weakReference = new WeakReference<>(getAvoidOnResultFragment(activity));
    }
}
