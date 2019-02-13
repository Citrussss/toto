package com.union.bangbang.todokotlin.ui.user.login

import android.app.Application
import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.UserEntity
import com.union.bangbang.todokotlin.base.model.BaseModel
import javax.inject.Inject

/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   13:53
 */
class LoginModel @Inject constructor(private val dataService: DataService, app: Application) : BaseModel(app) {
    var mobile = ObservableField<String>()
    var password = ObservableField<String>()
    fun onLoginClick(view: View) {
        Log.i("LoginModel", "onLoginClick")
        if (!TextUtils.isEmpty(mobile.get()) && !TextUtils.isEmpty(password.get())) {
            val user = UserEntity(0, mobile.get()!!, password.get()!!)
            addDisposable(dataService.login(user).subscribe(
                    {
                        finish()
                    }, {
                Log.e("LoginModel", it.message)
            }
            ))
        }
    }
}