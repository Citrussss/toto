
import android.app.Application
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.dagger.module.ActivityModule
import com.union.bangbang.todokotlin.dagger.module.DataModule
import com.union.bangbang.todokotlin.dagger.module.FragmentModule
import com.union.bangbang.todokotlin.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (DataModule::class),
    (ActivityModule::class),
    (FragmentModule::class),
    (ViewModelModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: TodoApplication)
}