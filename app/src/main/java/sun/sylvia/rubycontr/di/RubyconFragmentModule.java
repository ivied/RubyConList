package sun.sylvia.rubycontr.di;

import dagger.Binds;
import dagger.Module;
import sun.sylvia.rubycontr.di.scope.FragmentScope;
import sun.sylvia.rubycontr.ui.RubyconContract;
import sun.sylvia.rubycontr.ui.RubyconFragment;
import sun.sylvia.rubycontr.ui.RubyconPresenter;

/**
 * Created on 7/28/2017.
 */

@FragmentScope
@Module
public abstract class RubyconFragmentModule {

  @FragmentScope
  @Binds
  abstract RubyconContract.View provideRubyconFragment(RubyconFragment fragment);

  @FragmentScope
  @Binds
  abstract RubyconContract.Presenter provideRubyconPresenter(RubyconPresenter presenter);

}
