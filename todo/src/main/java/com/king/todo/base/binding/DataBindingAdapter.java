package com.king.todo.base.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binding.model.util.BaseUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;



/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:51
 * modify developer：  admin
 * modify time：9:51
 * modify remark：
 *
 * @version 2.0
 */

public class DataBindingAdapter {


    @BindingAdapter({"android:background"})
    public static void setBackground(View view, String url) {
        Context mContext = view.getContext();
        Glide.with(mContext)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(resource);
                        } else {
                            view.setBackgroundDrawable(resource);
                        }
                    }
                });
    }

    @BindingAdapter("file")
    public static void setFilePath(ImageView view, String path) {
        if (Patterns.WEB_URL.matcher(path).matches()) {
            Glide.with(view.getContext()).load(path).into(view);
        } else {
            Glide.with(view.getContext()).load(new File(path)).into(view);
        }
    }

    @BindingAdapter("background_file")
    public static void setBackGroudFile(ImageView view, String url) {
        if (url == null) { return; }
        Context mContext = view.getContext();
        Glide.with(mContext)
                .load(new File(url))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                            view.setBackground(resource);
                        else view.setBackgroundDrawable(resource);
                    }
                });
    }


    //    --------------------------ProgressBar------------------------------
    @BindingAdapter("android:secondaryProgress")
    public static void setSecondaryProgress(ProgressBar bar, int progress) {
        bar.setSecondaryProgress(progress);
    }

    //    --------------------------ImageView------------------------
    @BindingAdapter("src")
    public static void setImageDrawable(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)//优先级
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);//缓存策略
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    @BindingAdapter("loc_src")
    public static void setImageDrawableBymipmap(ImageView imageView, @DrawableRes Integer url) {
        imageView.setImageResource(url);
    }

    @BindingAdapter("circle")
    public static void setImageCircleDrawable(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new CircleCrop());//转化为圆角
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    @BindingAdapter("company_head")
    public static void company_head(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Context context = imageView.getContext();
            Glide.with(context).clear(imageView);
            RequestOptions options = new RequestOptions()
                    .centerCrop()

                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(new CircleCrop());
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }

    @BindingAdapter("head")
    public static void head(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Context context = imageView.getContext();
            Glide.with(context).clear(imageView);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(new CircleCrop());
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }


    public static RequestOptions getCircleOptions() {
        return new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new CircleCrop());//转化为圆角
    }


    @BindingAdapter({"android:src", "radius"})
    public static void setImageRadiusDrawable(ImageView imageView, String url, int radius) {

        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        radius = (int) (radius * BaseUtil.getDisplayMetrics(context).density + 0.5f);
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new RoundedCorners(radius));//转化为圆角
        Glide.with(context)
                .load(url)
                .apply(options2)
                .into(imageView);
    }


    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, @DrawableRes int mipmapId) {
        if (mipmapId != 0) {view.setImageResource(mipmapId);}
    }


    //    --------------------------TextView------------------------

    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, String url) {
        Context mContext = view.getContext();

        Glide.with(mContext).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                Drawable[] drawables = view.getCompoundDrawables();
                view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
            }
        });
    }


    @BindingAdapter({"android:text"})
    public static void setText(TextView textView, SpannableStringBuilder style) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }
//


    @BindingAdapter("onTouch")
    public static void onTouch(View view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }
   /* @BindingAdapter("viewpager")
    public static void setupWithViewPager(TabLayout layout, @IdRes int id){
        layout.setupWithViewPager(R.l);
    }*/
}

//    private static void loadImageFile(ImageView imageView, @Nullable String url, Consumer<File> consumer) {
//        Context context = imageView.getContext();
//        Glide.with(context).clear(imageView);
//
//        Glide.with(context)
//                .downloadOnly()
//                .load(url)
//                .apply(getCircleOptions())
//                .into(new SimpleTarget<File>() {
//                    @Override
//                    public void onResourceReady(File resource, Transition<? super File> transition) {
//                        head(imageView, resource.getAbsolutePath());
//                        Disposable subscribe = Observable.just(resource)
//                                .subscribeOn(Schedulers.io())
//                                .map(JimUtils::compressImage)
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(consumer, BaseUtil::toast);
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                    }
//
//
//                });
//    }

//    public static boolean updateHead(ImageView imageView, @Nullable String url, UpdateMobileImage mobile, Consumer<File> consumer) {
//        Uri uri = Uri.parse(url);
//        String objectKey = mobile.objectKey(Constant.start);
//        boolean register = objectKey.equals(uri.getLastPathSegment());
//        if (register) {
//            head(imageView, url);
//            mobile.objectKey(Constant.success);
//        } else {
//            loadImageFile(imageView, url, consumer);
//        }
//        return register;
//    }