package sun.sylvia.rubycontr.github;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Created on 12/2/2017.
 */

public interface GithubApi {

  @GET("/repos/{owner}/{repo}/stats/contributors")
  Observable<List<ContributorStats>>  contributorsStats(@Path("owner") String owner, @Path("repo") String repo);
}
