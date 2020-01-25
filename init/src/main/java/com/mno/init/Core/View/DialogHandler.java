package com.mno.init.Core.View;

import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import java.util.Set;

import com.mno.init.Core.Module.ActivityBase;

/**
 * Created by pablo on 28/08/17.
 */

public class DialogHandler extends WidgetHandler<AlertDialog> {

    public DialogHandler(ActivityBase activityBase){
        super(activityBase);
    }

    @Override
    public void hideAll() {
        for (AlertDialog alert : getAll()) {
            alert.dismiss();
        }
    }

    private String patchTraslate(String text){

        if(text != null && text.contains("Unable to")){
            text = "Error: Revisa tu conexión a internet.";
        }

        return text;
    }

    @Override
    public void showWidget(String alias) {
        if (widgetExists(alias)) {
            getWidget(alias).show();
            getStates().put(alias, true);
        }
    }

    public void showWidget(String alias, String message) {
        if (widgetExists(alias)) {
            message = patchTraslate(message);

            AlertDialog dialog = getWidget(alias);
            dialog.setMessage(message);
            dialog.show();
            getStates().put(alias, true);
        }
    }

    public void showWidget(String alias, String title, String message) {
        if (widgetExists(alias)) {
            message = patchTraslate(message);

            AlertDialog dialog = getWidget(alias);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.show();
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
            AlertDialog dialog = getWidget(alias);
            if (dialog.isShowing()) {
                showWidget(alias);
            } else {
                dismissWidget(alias);
            }
        }
    }

    @Override
    public void restoreStates() {
        Set<String> keys = getStates().keySet();
        boolean state;
        for (String key : keys) {
            state = getStates().get(key);

            if(state)
                showWidget(key);
            else
                dismissWidget(key);
        }
    }

    public AlertDialog addWidget(
            final String alias,
            String title,
            String message,
            String acceptTitle,
            DialogInterface.OnClickListener accept,
            String cancelTitle,
            DialogInterface.OnClickListener cancel,
            String neutralTitle,
            DialogInterface.OnClickListener neutral,
            boolean cancelable,
            View view) {

        AlertDialog alert = null;

        if (getContext() != null && !getContext().isFinishing()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            alert = builder.create();
            builder.setCancelable(cancelable);

            alert.setTitle(title);
            alert.setMessage(message);

            if (accept != null) {
                alert.setButton(DialogInterface.BUTTON_POSITIVE, acceptTitle, accept);
            }

            if (cancel != null) {
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, cancelTitle, cancel);
            }

            if (neutral != null) {
                alert.setButton(DialogInterface.BUTTON_POSITIVE, neutralTitle, neutral);
            }

            if(view != null){
                alert.setView(view);
            }

            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    getStates().put(alias, false);
                }
            });

            getWidgets().put(alias, alert);
        }

        return alert;
    }

    public AlertDialog addWidget(
            String alias,
            String title,
            String message) {

        return addWidget(alias, title, message, null, null, null, null, null, null, false, null);
    }

    public AlertDialog addWidget(
            String alias,
            String title,
            String message,
            String acceptTitle,
            DialogInterface.OnClickListener accept) {

        return addWidget(alias, title, message, acceptTitle, accept, null, null, null, null, false, null);
    }

    public AlertDialog addWidget(
            String alias,
            String title,
            String message,
            String acceptTitle,
            DialogInterface.OnClickListener accept,
            String cancelTitle,
            DialogInterface.OnClickListener cancel) {

        return addWidget(alias, title, message, acceptTitle, accept, cancelTitle, cancel, null, null, false, null);
    }

}
