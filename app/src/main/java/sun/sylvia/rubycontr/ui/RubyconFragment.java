package sun.sylvia.rubycontr.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import sun.sylvia.rubycontr.R;
import sun.sylvia.rubycontr.github.Contributor;
import sun.sylvia.rubycontr.github.GithubApi;

import static java.lang.String.format;

/**
 * Created on 12/2/2017.
 */

public class RubyconFragment extends Fragment implements RubyconContract.View{

  private static final String TAG = "RubyconFragment";

  @BindView(R.id.list) ListView contributorsList;

  @Inject RubyconContract.Presenter presenter;
  @Inject GithubApi githubApi;

  private ArrayAdapter<String> adapter;
  private CompositeDisposable disposables;
  private Unbinder unbinder;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Just token from test account
    String githubToken = getResources().getString(R.string.github_oauth_token);
    disposables = new CompositeDisposable();
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View layout = inflater.inflate(R.layout.fragment_retrofit, container, false);
    unbinder = ButterKnife.bind(this, layout);
    adapter = new ArrayAdapter<>(getActivity(), R.layout.item_log, R.id.item_log, new ArrayList<>());
    contributorsList.setAdapter(adapter);
    getListRubyContrib();

    return layout;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    disposables.dispose();
  }

  public void getListRubyContrib() {

    disposables.add(
        githubApi.contributors(getString(R.string.ruby), getString(R.string.ruby))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<Contributor>>() {

              @Override public void onComplete() {
                Log.d(TAG, "Retrofit call completed");
              }

              @Override public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
                Toast.makeText(getActivity(), "Something went wrong, check the code", Toast.LENGTH_LONG).show();
              }

              @Override public void onNext(List<Contributor> contributors) {
                for (Contributor c : contributors) {
                  adapter.add(format(Locale.UK, "%s has made %d contributions to %s", c.login, c.contributions, getString(R.string.ruby)));
                }
              }
            }));
  }


  @Override
  public void onAttach(Context context) {
    AndroidInjection.inject(this);
    super.onAttach(context);
  }

}