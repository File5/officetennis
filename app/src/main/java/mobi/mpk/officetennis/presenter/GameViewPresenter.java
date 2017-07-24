package mobi.mpk.officetennis.presenter;

import mobi.mpk.officetennis.model.Game;
import mobi.mpk.officetennis.view.GameView;

public class GameViewPresenter implements GameViewListener {

    private GameView view;
    private Game game;

    public GameViewPresenter() {
        game = new Game();
    }

    @Override
    public void onViewAttached(GameView view) {
        this.view = view;

        view.setScore(new GameView.Score(game.getHost(), game.getPartner()));
    }

    @Override
    public void onGameCancelled() {
        game.setDeleted(true);

        view.setDeleted(true);
        view.setConfirmMode();
    }

    @Override
    public void onGameFinished() {
        GameView.Score score = view.getScore();

        game.setHost(score.host);
        game.setPartner(score.partner);

        view.setConfirmMode();
    }

    @Override
    public void onGameEdit() {
        view.setScore(new GameView.Score(game.getHost(), game.getPartner()));
        view.setEditMode();
    }

    @Override
    public void onGameConfirmed() {
        view.closeView();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
