package com.mno.init.Core.View;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.mno.init.Core.Module.ActivityBase;
import com.mno.init.Core.Object.MLog;


public abstract class WidgetHandler <T> {

    private Hashtable<String, T> widgets = null;
    private ActivityBase activityBase = null;
    private Hashtable<String, Boolean> states;

    public WidgetHandler(ActivityBase activityBase) {
        this.activityBase = activityBase;
        this.widgets = new Hashtable<>();
        this.states = new Hashtable<>();
    }

    public Hashtable<String, T> getWidgets(){
        return this.widgets;
    }

    public Hashtable<String, Boolean> getStates(){
        return this.states;
    }

    public ActivityBase getContext(){
        return this.activityBase;
    }

    public void destroyAll() {
        hideAll();
        this.widgets = new Hashtable<>();
        this.states = new Hashtable<>();
    }

    public List<T> getAll() {
        Set<String> keys = this.widgets.keySet();
        List<T> all = new ArrayList<>();

        for (String key : keys) {
            all.add(this.widgets.get(key));
        }

        return all;
    }

    public boolean widgetExists(String alias) {
        return this.widgets.containsKey(alias);
    }

    public T getWidget(String alias) {
        T widget = null;

        if (widgetExists(alias)) {
            widget = this.widgets.get(alias);
        } else {
            MLog.e("widget_not_found", alias);
        }

        return widget;
    }

    public abstract void hideAll();
    public abstract void showWidget(String alias);
    public abstract void dismissWidget(String alias);
    public abstract void toggleWidget(String alias);
    //public abstract void saveStates();
    public abstract void restoreStates();

}
