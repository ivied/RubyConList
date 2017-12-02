package sun.sylvia.rubycontr.ui;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import sun.sylvia.rubycontr.github.ContributorStats;
import sun.sylvia.rubycontr.github.GithubApi;

import static java.lang.String.format;

/**
 * Created on 12/2/2017.
 */

public class RubyconPresenter implements RubyconContract.Presenter {

  private static final String TAG = "RubyconPresenter";
  private static final String RUBY = "ruby";

  private final RubyconContract.View mView;
  private CompositeDisposable disposables;

  @Inject GithubApi githubApi;

  @Inject
  RubyconPresenter(RubyconContract.View view) {
    mView = view;
  }

  @Override
  public void downloadContributors() {

    disposables = new CompositeDisposable();
    getListRubyContrib();
  }

  private void getListRubyContrib() {

    disposables.add(
            githubApi.contributorsStats(RUBY, RUBY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<ContributorStats>>() {

                      @Override public void onComplete() {
                        Log.d(TAG, "Retrofit call completed");
                      }

                      @Override public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        switch (((HttpException) e).code()) {
                            case 401:
                                mView.showToast("Check your auth github token");
                                break;
                            default:
                                mView.showToast("Problem with connection to github");
                        }
                      }

                      @Override public void onNext(List<ContributorStats> contributors) {
                        mView.showList(cutList(contributors));
                      }
                    }));
  }

    private List<ContributorStats> cutList(List<ContributorStats> contributors) {
        Collections.reverse(contributors);
        return contributors.subList(0, 25);

    }
}
