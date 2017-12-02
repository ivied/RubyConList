package sun.sylvia.rubycontr;

import android.app.Activity;
import android.app.Application;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;
import sun.sylvia.rubycontr.di.ApplicationComponent;
import sun.sylvia.rubycontr.di.ApplicationModule;
import sun.sylvia.rubycontr.di.DaggerApplicationComponent;

/**
 * Created on 12/2/2017.
 */

public class RubyconApplication extends Application implements HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

  @Override public void onCreate() {
    super.onCreate();
    ApplicationComponent component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
        .build();
    component.inject(this);
  }


  @Override public AndroidInjector<Activity> activityInjector() {
    return dispatchingActivityInjector;
  }
}