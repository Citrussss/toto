
import android.app.Application
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.dagger.module.ActivityModule
import com.union.bangbang.todokotlin.dagger.module.AppModule
import com.union.bangbang.todokotlin.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class),(ActivityModule::class),(ViewModelModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: TodoApplication)
}