package com.union.bangbang.todokotlin.ui.user.login

import android.app.Application
import android.content.Intent
import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.UserEntity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.base.utils.UserUtil.setToken
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.home_page
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_register
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import android.support.v4.content.ContextCompat.startActivity
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.R
import android.widget.EditText
import com.union.bangbang.todokotlin.ui.home.HomeActivity
import com.union.bangbang.zero.AppUtil


/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   13:53
 */
class LoginModel @Inject constructor(private val dataService: DataService, val app: Application) : BaseModel(app) {

    var mobile = ObservableField<String>()
    var password = ObservableField<String>()
    fun onLoginClick(view: View) {
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            val user = UserEntity(0, mobile.get()!!, password.get()!!)
            addDisposable(dataService.login(user).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        if (it.code == 0) {
                            setToken(app,it.data.token.token)
                            Toasty.success(getApplication(), "登录成功", Toast.LENGTH_SHORT, true).show()
//                            val intent = Intent(app, HomeActivity::class.java)
//                            AppUtil.peekActivity().startActivity(intent)
                            ArouterUtil.navigation(home_page)
//                            finish()
                        } else Toasty.error(getApplication(), it.toString(), Toast.LENGTH_SHORT, true).show()
                    },
                    {
                        Log.e("LoginModel", it.message)
                    }
            ))
        }
    }

    fun onGoRegisterClick(view: View) {
        ArouterUtil.navigation(user_register)
    }

    fun onRegisterClick(view: View) {
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            val user = UserEntity(0, mobile.get()!!, password.get()!!)
            addDisposable(dataService.register(user).subscribe(
                    {
                        Toasty.success(getApplication(), it.toString(), Toast.LENGTH_SHORT, true).show();
                    },
                    {
                        Log.e("LoginModel", it.message)
                    }
            ))
        }
    }

    fun onFinishClick(view:View){
        finish()
    }
}