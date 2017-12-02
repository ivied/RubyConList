package sun.sylvia.rubycontr.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import sun.sylvia.rubycontr.di.scope.ActivityScoupe;
import sun.sylvia.rubycontr.ui.RepoContributorsActivity;
import sun.sylvia.rubycontr.ui.RubyconFragmentProvider;

/**
 * Created on 12/2/2017.
 */

@ActivityScoupe
@Module
abstract class ActivityModule {


  @ActivityScoupe
  @ContributesAndroidInjector(modules = RubyconFragmentProvider.class)
  abstract RepoContributorsActivity contributesDashboardActivity();


}