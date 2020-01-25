package com.mno.init.Core.View;

import android.app.ProgressDialog;

import java.util.Set;

import com.mno.init.Core.Module.ActivityBase;
import com.mno.init.Core.Object.MLog;

/**
 * Created by pablo on 28/08/17.
 */

public class ProgressDialogHandler extends WidgetHandler<ProgressDialog> {

    public ProgressDialogHandler(ActivityBase activityBase){
        super(activityBase);
    }

    @Override
    public void hideAll() {
        for (ProgressDialog progress : getAll()) {
            progress.dismiss();
        }
    }

    @Override
    public void showWidget(String alias) {
        if (widgetExists(alias)) {
            getWidget(alias).show();
            getStates().put(alias, true);
        }
    }

    @Override
    public void dismissWidget(String alias) {
        if (widgetExists(alias)) {
            getWidget(alias).dismiss();
            getStates().put(alias, false);
        }
    }

    @Override
    public void toggleWidget(String alias) {
        if (widgetExists(alias)) {
            ProgressDialog progress = getWidget(alias);
            if (progress.isShowing()) {
                dismissWidget(alias);
            } else {
                showWidget(alias);
            }
        }
    }

    @Override
    public void restoreStates() {
        Set<String> keys = getStates().keySet();
        boolean state;
        for (String key : keys) {
            state = getStates().get(key);
            MLog.e("restore_"+key,  getWidgets().get(key).isShowing()+"");
            if(state)
                showWidget(key);
            else
                dismissWidget(key);
        }
    }

    public ProgressDialog addWidget(
            String alias,
            String title,
            String message,
            boolean cancelable) {

        ProgressDialog progress = null;

        if (getContext() != null && !getContext().isFinishing()) {

            progress = new ProgressDialog(getContext());
            progress.setCancelable(cancelable);

            progress.setTitle(title);
            progress.setMessage(message);

            getWidgets().put(alias, progress);
        }

        return progress;
    }

    public ProgressDialog addWidget(
            String alias,
            String title,
            String message) {

        return addWidget(alias, title, message, false);
    }

}
