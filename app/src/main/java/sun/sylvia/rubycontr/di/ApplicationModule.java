package sun.sylvia.rubycontr.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import sun.sylvia.rubycontr.di.scope.ApplicationScope;

/**
 * Created on 12/2/2017.
 */

@Module
public class ApplicationModule {

  private Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  @ApplicationScope Context provideContext() {
    return mApplication;
  }

}
