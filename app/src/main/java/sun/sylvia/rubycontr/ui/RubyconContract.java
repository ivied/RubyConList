package sun.sylvia.rubycontr.ui;

import java.util.ArrayList;
import java.util.List;

import sun.sylvia.rubycontr.github.ContributorStats;

/**
 * Created on 12/2/2017.
 */

public interface RubyconContract {

  public interface View {

    void showToast(String text);

    void showList(List<ContributorStats> contributors);
  }

  public interface Presenter {

    void downloadContributors();
  }
}
