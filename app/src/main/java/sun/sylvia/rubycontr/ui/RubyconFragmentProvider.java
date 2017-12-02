package sun.sylvia.rubycontr.ui;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import sun.sylvia.rubycontr.di.RubyconFragmentModule;
import sun.sylvia.rubycontr.di.scope.FragmentScope;
import sun.sylvia.rubycontr.ui.RubyconFragment;

/**
 * Created on 12/2/2017.
 */

@FragmentScope
@Module
public abstract class RubyconFragmentProvider {

  @FragmentScope
  @ContributesAndroidInjector(modules = RubyconFragmentModule.class)
  abstract RubyconFragment provideMainTopFragment();

}
