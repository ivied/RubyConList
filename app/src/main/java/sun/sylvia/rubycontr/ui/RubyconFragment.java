package sun.sylvia.rubycontr.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import sun.sylvia.rubycontr.github.ContributorStats;
import sun.sylvia.rubycontr.github.GithubApi;
import sun.sylvia.rubycontr.ui.adapter.ContributorsStatsAdapter;

import static java.lang.String.format;

/**
 * Created on 12/2/2017.
 */

public class RubyconFragment extends Fragment implements RubyconContract.View{

  private static final String TAG = "RubyconFragment";

  @BindView(R.id.list) RecyclerView contributorsList;

  @Inject RubyconContract.Presenter presenter;
  @Inject GithubApi githubApi;

  private ContributorsStatsAdapter mAdapter;
  private LinearLayoutManager linearLayoutManager;
  private CompositeDisposable disposables;
  private Unbinder unbinder;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View layout = inflater.inflate(R.layout.fragment_retrofit, container, false);
    unbinder = ButterKnife.bind(this, layout);

    initiate();

    return layout;
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  private void initiate() {
    initiateRecyclerView();
    contributorsList.setAdapter(mAdapter);
  }

  private void initiateRecyclerView() {
    mAdapter = new ContributorsStatsAdapter(new ArrayList<>(), presenter);
    linearLayoutManager = new LinearLayoutManager(getActivity());
    contributorsList.setLayoutManager(linearLayoutManager);
    contributorsList.setHasFixedSize(true);
    contributorsList.setItemAnimator(new DefaultItemAnimator());
    contributorsList.setAdapter(mAdapter);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    disposables.dispose();
  }



  @Override
  public void onAttach(Context context) {
    AndroidInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public void showToast(String text) {

    Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showList(List<ContributorStats> contributors) {
    mAdapter.updateData(contributors);
  }
}