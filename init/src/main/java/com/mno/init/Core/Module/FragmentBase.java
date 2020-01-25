package com.mno.init.Core.Module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mno.init.Core.Animation.FragmentAnimation;
import com.mno.init.Core.Interface.OnBackPress;
import com.mno.init.Core.Interface.OnLoadFragment;
import com.mno.init.Core.Object.MLog;
import com.mno.init.Core.View.Ui;

public class FragmentBase extends Fragment implements OnBackPress {

    private Ui ui;
    private int viewId;
    private FragmentAnimation animation;
    private int layoutView;
    private boolean replaceAll = false;
    private ActivityBase activityBase;
    private OnLoadFragment onLoadFragment = null;
    private boolean paused = false;
    private boolean run = false;
    private boolean checkState = true;

    public void setReplaceAll(boolean replaceAll){
        this.replaceAll = replaceAll;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public int getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(int layoutView) {
        this.layoutView = layoutView;
    }

    public FragmentAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(FragmentAnimation animation) {
        this.animation = animation;
    }

    public FragmentBase(){
        setUi(new Ui(this.getContext()));
    }

    public FragmentBase getSelf(){
        return this;
    }

    public boolean isCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }

    public OnLoadFragment getOnLoadFragment() {
        return onLoadFragment;
    }

    public void setOnLoadFragment(OnLoadFragment onLoadFragment) {
        this.onLoadFragment = onLoadFragment;
    }

    public ActivityBase getActivityBase() {
        ActivityBase activity;
        if(activityBase == null){
            activity = (ActivityBase) getActivity();
        }
        else {
            activity = activityBase;
        }

        return activity;
    }

    public void setActivityBase(ActivityBase activityBase) {
        this.activityBase = activityBase;
    }

    public void onViewReady(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutView(), container, false);
        getUi().setView(view);
        onViewReady();
        if(getOnLoadFragment() != null){
            getOnLoadFragment().onViewReady(getSelf(), getUi());
        }
        return view;

    }

    public void transaction(ActivityBase activityBase, FragmentBase fragmentBase, boolean backstack) {

        FragmentAnimation animation = getAnimation();
        setActivityBase(activityBase);

        if (activityBase != null && !activityBase.isFinishing() && !activityBase.isFinishing()) {

            FragmentManager manager = activityBase.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            if(fragmentBase != null) {
                transaction.hide(fragmentBase);
            }

            if (backstack) {
                transaction.addToBackStack(this.getClass().getCanonicalName());
            }

            if (getAnimation() != null) {
                transaction.setCustomAnimations(animation.getEnter(), animation.getExit(), animation.getPopEnter(), animation.getPopExit());
            }

            if (replaceAll) {
                transaction.replace(getViewId(), this, this.getClass().getCanonicalName());
            } else {
                transaction.add(getViewId(), this, this.getClass().getCanonicalName());
            }

            try{

                if(!isCheckState()) {
                    transaction.commitAllowingStateLoss();
                }
                else {
                    transaction.commit();
                }

            }
            catch (Exception e){
                MLog.e("ERROR", e.getMessage());
            }

        }



    }

    public void transaction(FragmentBase fragmentBase, boolean backstack){
        transaction(fragmentBase.getActivityBase(), fragmentBase, backstack);
    }

    public boolean isRunning(){
        return this.run;
    }

    public boolean isPaused(){
        return this.paused;
    }

    @Override
    public boolean onBack() {
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        this.run = true;
    }

    @Override
    public void onStop(){
        super.onStop();
        this.run = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        this.paused = false;
    }

    @Override
    public void onPause(){
        super.onPause();
        this.paused = true;
    }

}
