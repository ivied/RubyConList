package sun.sylvia.rubycontr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import javax.inject.Inject;
import sun.sylvia.rubycontr.R;

public class RepoContributorsActivity extends AppCompatActivity  implements HasFragmentInjector {


  @Inject DispatchingAndroidInjector<android.app.Fragment> fragmentInjector;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_contributors);

    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction().replace(R.id.content, new RubyconFragment(), this.toString()).commit();
    }
  }


  @Override public AndroidInjector<android.app.Fragment> fragmentInjector() {
    return fragmentInjector;
  }
}
