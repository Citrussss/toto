
import android.app.Application
import android.content.Context
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
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
//        @BindsInstance
//        fun appModule(appModule: AppModule):Builder
        fun build(): AppComponent
    }
    fun getContext(): Context

    fun inject(application: TodoApplication)
}