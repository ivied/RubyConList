package sun.sylvia.rubycontr.ui;

import javax.inject.Inject;

/**
 * Created on 12/2/2017.
 */

public class RubyconPresenter implements RubyconContract.Presenter {

  private final RubyconContract.View view;

  @Inject
  RubyconPresenter(RubyconContract.View view) {
    this.view = view;
  }
}
