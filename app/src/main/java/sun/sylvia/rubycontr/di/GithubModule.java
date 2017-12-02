package sun.sylvia.rubycontr.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sun.sylvia.rubycontr.R;
import sun.sylvia.rubycontr.di.scope.ApplicationScope;
import sun.sylvia.rubycontr.github.GithubApi;
import sun.sylvia.rubycontr.github.GithubService;

/**
 * Created on 12/2/2017.
 */
@Module(includes = ApplicationModule.class)
class GithubModule {
  @Provides @ApplicationScope GithubApi provideGithubService(Context context) {
    return new GithubService(context.getResources().getString(R.string.github_oauth_token)).createGithubService();
  }

}
