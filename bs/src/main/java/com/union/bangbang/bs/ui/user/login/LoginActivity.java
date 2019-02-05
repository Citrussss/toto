package com.union.bangbang.bs.ui.user.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.union.bangbang.base.base.BaseActivity;
import com.union.bangbang.base.base.BaseModel;
import com.union.bangbang.bs.R;

import javax.inject.Inject;

/**
 * 不乱于心，不困于情。不畏将来，不念过往。如此，安好!
 * <p>
 * 深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!
 * <p>
 * 善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!
 * <p>
 * 勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!
 * <p>
 * 无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
public class LoginActivity extends BaseActivity {
    @Inject BaseModel baseModel;
    private String TAG ="LoginActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: "+baseModel.getS());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
