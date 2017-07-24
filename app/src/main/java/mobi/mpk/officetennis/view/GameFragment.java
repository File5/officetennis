package mobi.mpk.officetennis.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shawnlin.numberpicker.NumberPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobi.mpk.officetennis.R;
import mobi.mpk.officetennis.presenter.GameViewListener;
import mobi.mpk.officetennis.presenter.GameViewPresenter;

public class GameFragment extends Fragment implements GameView {

    @BindView(R.id.score_game_layout)
    View scoreGameLayout;

    @BindView(R.id.hostScorePicker)
    NumberPicker hostScorePicker;

    @BindView(R.id.partnerScorePicker)
    NumberPicker partnerScorePicker;

    @BindView(R.id.deleted_game_layout)
    View deletedGameLayout;

    @BindView(R.id.edit_mode_layout)
    View editModeLayout;

    @BindView(R.id.cancel_game_button)
    Button cancelGameButton;

    @BindView(R.id.finish_game_button)
    Button finishGameButton;

    @BindView(R.id.confirm_mode_layout)
    View confirmModeLayout;

    @BindView(R.id.edit_game_button)
    Button editGameButton;

    @BindView(R.id.confirm_game_button)
    Button confirmGameButton;

    GameViewListener listener;

    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listener = new GameViewPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, view);
        hostScorePicker.setMaxValue(5);
        hostScorePicker.setWrapSelectorWheel(false);
        partnerScorePicker.setMaxValue(5);
        partnerScorePicker.setWrapSelectorWheel(false);

        listener.onViewAttached(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        listener.onViewDetached();

        super.onDestroyView();
    }

    @Override
    public Score getScore() {
        int host = hostScorePicker.getValue();
        int partner = partnerScorePicker.getValue();

        return new Score(host, partner);
    }

    @Override
    public void setScore(Score score) {
        deletedGameLayout.setVisibility(View.GONE);
        scoreGameLayout.setVisibility(View.VISIBLE);

        hostScorePicker.setValue(score.host);
        partnerScorePicker.setValue(score.partner);
    }

    @Override
    public void setDeleted(boolean deleted) {
        if (deleted) {
            scoreGameLayout.setVisibility(View.GONE);
            deletedGameLayout.setVisibility(View.VISIBLE);
        } else {
            deletedGameLayout.setVisibility(View.GONE);
            scoreGameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setEditMode() {
        confirmModeLayout.setVisibility(View.GONE);
        editModeLayout.setVisibility(View.VISIBLE);

        hostScorePicker.setEnabled(true);
        partnerScorePicker.setEnabled(true);
    }

    @Override
    public void setConfirmMode() {
        editModeLayout.setVisibility(View.GONE);
        confirmModeLayout.setVisibility(View.VISIBLE);

        hostScorePicker.setEnabled(false);
        partnerScorePicker.setEnabled(false);
    }

    @Override
    public void closeView() {
        getActivity().finish();
    }

    @OnClick(R.id.cancel_game_button)
    void onCancelGame() {
        listener.onGameCancelled();
    }

    @OnClick(R.id.finish_game_button)
    void onFinishGame() {
        listener.onGameFinished();
    }

    @OnClick(R.id.edit_game_button)
    void onEditGame() {
        listener.onGameEdit();
    }

    @OnClick(R.id.confirm_game_button)
    void onConfirmGame() {
        listener.onGameConfirmed();
    }
}
