package com.mno.init.Core.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Calendar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mno.init.Core.Object.Util;


/**
 * Created by Pablo on 09/12/2016.
 */

public class Ui {

    public static final int BUTTON_LEFT = 0;
    public static final int BUTTON_CENTER = 1;
    public static final int BUTTON_RIGHT = 2;

    private Context context = null;
    private View view = null;

    public Ui(Context context, View view) {
        this.setContext(context);
        this.setView(view);
    }

    public Ui(Context context) {
        this.setContext(context);
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }
        }
        return views;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ImageView getImageView(int widgetId) {
        return (ImageView) this.getView().findViewById(widgetId);
    }

    public View getItemView(int widgetId) {
        return this.getView().findViewById(widgetId);
    }

    public EditText getEditText(int widgetId) {
        return (EditText) this.getView().findViewById(widgetId);
    }

    public CheckBox getCheckBox(int widgetId) {
        return (CheckBox) this.getView().findViewById(widgetId);
    }

    public Button getButton(int widgetId) {
        return (Button) this.getView().findViewById(widgetId);
    }

    public TextView getTextView(int widgetId) {
        return (TextView) this.getView().findViewById(widgetId);
    }

    public LinearLayout getLinearLayout(int widgetId) {
        return (LinearLayout) this.getView().findViewById(widgetId);
    }

    public GridLayout getGridLayout(int widgetId) {
        return (GridLayout) this.getView().findViewById(widgetId);
    }

    public RelativeLayout getRelativeLayout(int widgetId) {
        return (RelativeLayout) this.getView().findViewById(widgetId);
    }

    public DrawerLayout getDrawerLayout(int widgetId) {
        return (DrawerLayout) this.getView().findViewById(widgetId);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(int widgetId) {
        return (SwipeRefreshLayout) this.getView().findViewById(widgetId);
    }

    public Spinner getSpinner(int widgetId) {
        return (Spinner) this.getView().findViewById(widgetId);
    }

    public ProgressBar getProgressBar(int widgetId) {
        return (ProgressBar) this.getView().findViewById(widgetId);
    }

    public Toolbar getToolbar(int widgetId) {
        return (Toolbar) this.getView().findViewById(widgetId);
    }

    public NavigationView getNavigationView(int widgetId) {
        return (NavigationView) this.getView().findViewById(widgetId);
    }

    public BottomNavigationView getBottomNavigationView(int widgetId) {
        return (BottomNavigationView) this.getView().findViewById(widgetId);
    }

    public RecyclerView getRecyclerView(int widgetId) {
        return (RecyclerView) this.getView().findViewById(widgetId);
    }

    public ScrollView getScrollView(int widgetId) {
        return (ScrollView) this.getView().findViewById(widgetId);
    }

    public Switch getSwitch(int widgetId) {
        return (Switch) this.getView().findViewById(widgetId);
    }

    public WebView getWebView(int widgetId) {
        return (WebView) this.getView().findViewById(widgetId);
    }

    public String valueEditText(EditText edit){
        String value = "";

        if(edit != null && edit.getText() != null){
            value = edit.getText().toString().trim();
        }

        return value;
    }

    public String string(int stringId) {

        if (getContext() != null) {
            try {
                return getContext().getResources().getString(stringId);
            } catch (NullPointerException e) {
                return "ERROR";
            }
        } else {
            return "ERROR";
        }

    }

    public float dimen(int dimenId) {

        if (getContext() != null) {
            return getContext().getResources().getDimension(dimenId);
        } else {
            return 0;
        }

    }

    public int color(int colorId) {

        if (getContext() != null) {
            return getContext().getResources().getColor(colorId);
        } else {
            return 0;
        }

    }

    public Drawable drawable(int drawerId) {

        if (getContext() != null) {
            return getContext().getResources().getDrawable(drawerId);
        } else {
            return null;
        }

    }

    public void radioSimulator(View button, View parent, String tag, int type, int activeColor, int normalColor, int[] backgroundsActive, int[] backgrounds) {

        ArrayList<View> views = getViewsByTag((ViewGroup) parent, tag);

        Button general, active;
        active = (Button) button;
        normalColor = color(normalColor);

        for (int i = 0, size = views.size(); i < size; i++) {
            general = (Button) views.get(i);
            if (i == BUTTON_LEFT) {
                general.setBackground(drawable(backgrounds[BUTTON_LEFT]));
            } else if ((i == BUTTON_CENTER && size > 2) || (size > 2 && i < (size - 1))) {
                general.setBackground(drawable(backgrounds[BUTTON_CENTER]));
            } else {
                general.setBackground(drawable(backgrounds[BUTTON_RIGHT]));
            }

            general.setTextColor(normalColor);
        }

        active.setBackground(drawable(backgroundsActive[type]));
        active.setTextColor(color(activeColor));
    }

    public void radioSimulatorColor(View button, View parent, String tag, int type, int activeColor, int normalColor, int[] backgroundsActive, int[] backgrounds) {

        ArrayList<View> views = getViewsByTag((ViewGroup) parent, tag);

        Button general, active;
        active = (Button) button;
        normalColor = color(normalColor);

        for (int i = 0, size = views.size(); i < size; i++) {
            general = (Button) views.get(i);
            general.setBackgroundColor(color(backgrounds[type]));
            general.setTextColor(normalColor);
        }

        active.setBackgroundColor(color(backgroundsActive[type]));
        active.setTextColor(color(activeColor));
    }

    public static void setTimePickerDialog(final EditText text) {

        Calendar calendar = Calendar.getInstance();

        final TimePickerDialog timePickerDialog = new TimePickerDialog(text.getContext(), android.R.style.Theme_Holo_Light_Panel, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourInt, int minuteInt) {
                String minute, hour;
                minute = (minuteInt < 10) ? "0" + minuteInt : minuteInt + "";
                hour = (hourInt < 10) ? "0" + hourInt : hourInt + "";
                text.setText(hour + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }

    public static void setDatePickerDialog(EditText text){
        setDatePickerDialog(text, "yyyy-MM-dd", "dd/MM/yyyy", 18);
    }

    public static void setDatePickerDialog(final EditText text, final String dateIn, final String dateOut, int minYear){

        Calendar calendar = Calendar.getInstance();
        int year, month, day;
        String currentDate = (text.getText() == null) ? "" : text.getText().toString();

        if(!currentDate.isEmpty()){
            calendar = Util.dateToCalendar(currentDate, dateIn);
            year = calendar.get(Calendar.YEAR);
        }
        else{
            year = calendar.get(Calendar.YEAR) - minYear;
        }

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        final DatePickerDialog datePickerDialog = new DatePickerDialog(text.getContext(), android.R.style.Theme_Holo_Light_Panel, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String dayString  = (day < 10) ? "0"+day : ""+day;
                String monthString  = (month < 10) ? "0"+month : ""+month;
                String yearString = year+"";

                String dateFinal = dateOut;

                dateFinal = dateFinal.replace("dd", dayString);
                dateFinal = dateFinal.replace("MM", monthString);
                dateFinal = dateFinal.replace("yyyy", yearString);

                text.setText(dateFinal);
            }
        }, year, month, day);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }


}