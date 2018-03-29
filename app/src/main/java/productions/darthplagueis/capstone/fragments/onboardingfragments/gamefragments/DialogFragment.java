package productions.darthplagueis.capstone.fragments.onboardingfragments.gamefragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import productions.darthplagueis.capstone.R;

import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

/**
 *
 */
public class DialogFragment extends Fragment {

    private View rootView;

    private TextView greetingsText, triviaText;

    public DialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        setViews();
        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (rootView != null) {
                greetingsText.setText(getRandomText(rootView.getContext(), "greetings"));
                triviaText.setText(getRandomText(rootView.getContext(), "greetings"));
            }
        }
    }

    private void setViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable)
                rootView.findViewById(R.id.dialogue_layout).getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        greetingsText = rootView.findViewById(R.id.second_textview_dial);
        greetingsText.setText(getRandomText(rootView.getContext(), "greetings"));
        triviaText = rootView.findViewById(R.id.third_textview_dial);
        rootView.findViewById(R.id.ok_btn_dialogue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .hide(DialogFragment.this)
                            .commit();
                }
            }
        });
    }
}
