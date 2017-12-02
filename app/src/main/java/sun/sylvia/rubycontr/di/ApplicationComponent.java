package sun.sylvia.rubycontr.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import sun.sylvia.rubycontr.RubyconApplication;
import sun.sylvia.rubycontr.di.scope.ApplicationScope;

/**
 * Created on 12/2/2017.
 */

@ApplicationScope
@Component(modules = {ActivityModule.class, GithubModule.class})
public interface ApplicationComponent  extends AndroidInjector<RubyconApplication> {


}