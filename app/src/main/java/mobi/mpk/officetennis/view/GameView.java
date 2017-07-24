package mobi.mpk.officetennis.view;

public interface GameView {

    class Score {
        public final int host;
        public final int partner;

        public Score(int host, int partner) {
            this.host = host;
            this.partner = partner;
        }
    }

    Score getScore();
    void setScore(Score score);
    void setDeleted(boolean deleted);
    void setEditMode();
    void setConfirmMode();
    void closeView();

}
