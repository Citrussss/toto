package com.union.bangbang.todokotlin.ui.user.login

import android.app.Application
import android.databinding.ObservableField
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.UserEntity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.okhttp.Reaper
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import com.union.bangbang.todokotlin.base.utils.UserUtil.setToken
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.home_page
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_login
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_register
import com.union.bangbang.zero.AppUtil
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject


/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   13:53
 */
class LoginModel @Inject constructor(private val dataService: DataService, val app: Application) : BaseModel(app) {

    var mobile = ObservableField<String>()
    var password = ObservableField<String>()
    var signature = "本App设计于2019—2-27\n通信154\n金帮裕"
    var background = ObservableField<String>(Companion.background)
    fun onLoginClick(view: View) {
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            val user = UserEntity(null, mobile.get()!!, password.get()!!)
            dataService.login(user).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    Reaper(this, Consumer {
                        if (it.code == 0) {
                            setToken(app, it.data.token.token)
                            Toasty.success(getApplication(), "登录成功", Toast.LENGTH_SHORT, true).show()
                            ArouterUtil.navigation(home_page)
                        } else Toasty.error(getApplication(), it.toString(), Toast.LENGTH_SHORT, true).show()
                    },
                            Consumer {
                                Log.e("LoginModel", it.message)
                            })
            )
        }
    }

    fun onGoRegisterClick(view: View) {
        ArouterUtil.navigation(user_register)
    }

    fun onRegisterClick(view: View) {
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            val user = UserEntity(0, mobile.get()!!, password.get()!!)
            dataService.register(user).subscribe(
                    Reaper(this, Consumer {
                        Toasty.success(getApplication(), it.toString(), Toast.LENGTH_SHORT, true).show();
                    },
                            Consumer {
                                Log.e("LoginModel", it.message)
                            })
            )
        }
    }

    fun onFinishClick(view: View) {
        finish()
    }

    fun onEditPwdClick(view: View) {
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            var user = UserEntity(null, mobile.get()!!, password.get()!!)
            dataService.editPwd(user).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    Reaper(this, Consumer {
                        setToken(app, it.data.token.token)
                        ToastUtil.success("修改成功")
                        finish()
                    }, Consumer { ToastUtil::error })
            )
        }
    }

    public fun onExitClick(view: View) {
        setToken(app, "")
        ArouterUtil.navigation(user_login)
        for (activity in AppUtil.getActivityStack()) {
            if (activity !is LoginActivity) activity.finish()
        }
    }

    override fun attachData(bundle: Bundle) {
        super.attachData(bundle)
            background.set(Companion.background)
    }

    companion object {
        var background: String? = ""
    }
}