package mobi.mpk.officetennis.presenter;

import mobi.mpk.officetennis.view.GameView;

public interface GameViewListener {

    void onViewAttached(GameView view);
    void onGameCancelled();
    void onGameFinished();
    void onGameEdit();
    void onGameConfirmed();
    void onViewDetached();

}
