package com.mno.init.Core.Module;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.google.android.material.navigation.NavigationView;
import com.mno.init.Core.Interface.OnActivityResult;
import com.mno.init.Core.Interface.OnPermissionResult;
import com.mno.init.Core.Object.MLog;
import com.mno.init.Core.Object.Permission;
import com.mno.init.Core.View.DialogHandler;
import com.mno.init.Core.View.ProgressDialogHandler;
import com.mno.init.Core.View.Ui;
import com.mno.init.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Pablo on 09/12/2016.
 */

public class ActivityBase extends AppCompatActivity {

    public static final int BARCODE_READER_REQUEST_CODE = 49374;
    public static final int CAMERA_REQUEST_CODE = 30001;
    public static final int PHONE_REQUEST_CODE = 30002;

    private int activityLayout;
    private Ui ui;
    private Bundle params = new Bundle();
    private ActivityBase self = null;
    private SharedPreferences preferences;
    private HashMap<Integer, OnPermissionResult> requestedPermissions = new HashMap<>();
    private HashMap<String, Integer> requestedPermissionsCodes = new HashMap<>();
    private HashMap<Integer, OnActivityResult> resultActivity = new HashMap<>();
    private DialogHandler dialog = null;
    private ProgressDialogHandler progress = null;
    private DrawerLayout drawer = null;
    private Toolbar toolbar = null;
    private boolean paused = false;
    private boolean run = false;
    private ImageView customMenu;

    public ActivityBase getSelf() {
        return this;
    }

    public void setSelf(ActivityBase self) {
        this.self = self;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public Bundle getParams() {
        return params;
    }

    public void setParams(Bundle params) {
        this.params = params;
    }

    public ActivityBase() {
        setUi(new Ui(this));
    }

    public void setActivityLayout(int activityLayout) {
        this.activityLayout = activityLayout;
    }

    public int getActivityLayout() {
        return this.activityLayout;
    }

    public DialogHandler getDialog() {
        return dialog;
    }

    public ProgressDialogHandler getProgress() {
        return progress;
    }

    public DrawerLayout getDrawer() {
        return this.drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public int getPreference(String index, int defaultValue) {
        return preferences.getInt(index, defaultValue);
    }

    public String getPreference(String index, String defaultValue) {
        return preferences.getString(index, defaultValue);
    }

    public void savePreference(String index, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(index, value);
        editor.commit();
    }

    public void savePreference(String index, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(index, value);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        preferences = getPreferences(Context.MODE_PRIVATE);

        setSelf(this);
        setParams(getIntent().getExtras());
        setContentView(getActivityLayout());
        getUi().setView(findViewById(android.R.id.content));

        this.dialog = new DialogHandler(this);
        this.progress = new ProgressDialogHandler(this);

        requestedPermissionsCodes.put(Manifest.permission.ACCESS_COARSE_LOCATION, 1001);
        requestedPermissionsCodes.put(Manifest.permission.ACCESS_FINE_LOCATION, 1002);
        requestedPermissionsCodes.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1003);
        requestedPermissionsCodes.put(Manifest.permission.READ_EXTERNAL_STORAGE, 1004);
        requestedPermissionsCodes.put(Manifest.permission.CAMERA, 1005);
        requestedPermissionsCodes.put(Manifest.permission.READ_CONTACTS, 1006);
        requestedPermissionsCodes.put(Manifest.permission.RECEIVE_SMS, 1007);
        requestedPermissionsCodes.put(Manifest.permission.READ_SMS, 1008);
        requestedPermissionsCodes.put(Manifest.permission.WRITE_CONTACTS, 1009);
        requestedPermissionsCodes.put(Manifest.permission.CALL_PHONE, 1010);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public LinearLayoutManager getLinearLayoutManagerVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    public LinearLayoutManager getLinearLayoutManagerHorizontal() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return linearLayoutManager;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.run = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        this.run = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.paused = true;
        //getDialog().hideAll();
        //getProgress().hideAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.paused = false;
        //getDialog().restoreStates();
        //getProgress().restoreStates();
    }

    public boolean isRunning() {
        return this.run;
    }

    public boolean isPaused() {
        return this.paused;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getDialog().destroyAll();
        getProgress().destroyAll();
    }

    public void startOf(ActivityBase activity, boolean endLast, Integer flags) {

        Intent intent = new Intent(activity, this.getClass());
        if (getParams().size() > 0) {
            intent.putExtras(getParams());
        }

        if (flags != null) {
            intent.addFlags(flags.intValue());
        }

        activity.startActivity(intent);

        getParams().clear();
        if (endLast) {
            activity.finish();
        }

    }

    public void startOf(ActivityBase activity, Integer flags) {
        startOf(activity, false, flags);
    }

    public void startOf(ActivityBase activity) {
        startOf(activity, false, null);
    }

    public void startAndDestroy(ActivityBase activity, Integer flags) {
        startOf(activity, true, flags);
    }

    public void startAndDestroy(ActivityBase activity) {
        startOf(activity, true, null);
    }

    /* set Param */

    public void setParamByte(String index, byte value) {
        getParams().putByte(index, value);
    }

    public void setParamInteger(String index, int value) {
        getParams().putInt(index, value);
    }

    public void setParamString(String index, String value) {
        getParams().putString(index, value);
    }

    public void setParamChar(String index, char value) {
        getParams().putChar(index, value);
    }

    public void setParamFloat(String index, float value) {
        getParams().putFloat(index, value);
    }

    public void setParamDouble(String index, double value) {
        getParams().putDouble(index, value);
    }

    public void setParamBoolean(String index, boolean value) {
        getParams().putBoolean(index, value);
    }

    public void setParamObject(String index, Object value) {
        String className = this.getClass().getCanonicalName();

        if (Volatile.getObjectParams().containsKey(className)) {
            Volatile.getObjectParams().get(className).put(index, value);
        } else {
            Hashtable<String, Object> newHastable = new Hashtable<>();
            newHastable.put(index, value);
            Volatile.getObjectParams().put(className, newHastable);
        }
    }

    /* get Param */

    public Byte getParamByte(String index) {
        return getParams().getByte(index);
    }

    public int getParamInteger(String index) {
        return getParams().getInt(index);
    }

    public String getParamString(String index) {
        return getParams().getString(index);
    }

    public char getParamChar(String index) {
        return getParams().getChar(index);
    }

    public float getParamFloat(String index) {
        return getParams().getFloat(index);
    }

    public double getParamDouble(String index) {
        return getParams().getDouble(index);
    }

    public boolean getParamBoolean(String index) {
        return getParams().getBoolean(index);
    }

    public Object getParamObject(String index) {
        Object value = null;
        String className = this.getClass().getCanonicalName();

        if (Volatile.getObjectParams().containsKey(className)) {
            value = Volatile.getObjectParams().get(className).get(index);
        }

        return value;
    }

    public void goBack() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        if (isPaused() || !isRunning()) {
            MLog.e(getClass().getCanonicalName(), "paused");
        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            int fragmentCount = fragmentManager.getBackStackEntryCount();

            if (fragmentCount > 0) {

                FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(fragmentCount - 1);
                String fragmentTag = backEntry.getName();
                Fragment fragmentBase = fragmentManager.findFragmentByTag(fragmentTag);

                if (fragmentBase != null) {
                    FragmentBase fragment = (FragmentBase) fragmentBase;
                    if (fragment != null && !fragment.isPaused() && fragment.onBack()) {
                        goBack();
                    }
                } else {
                    goBack();
                }
            } else {
                goBack();
            }
        }
    }

    public boolean hasPermission(String permission) {
        return hasPermissions(new String[]{permission});
    }

    public boolean hasPermissions(String[] permissions) {
        boolean result = true;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                result = false;
                break;
            }
        }

        return result;
    }

    public void requestPermission(String[] permission, OnPermissionResult onPermissionResult) {

        Integer code = (permission.length > 0) ? requestedPermissionsCodes.get(permission[0]) : null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && code != null) {
            requestedPermissions.put(code.intValue(), onPermissionResult);
            this.requestPermissions(permission, code.intValue());
        } else {
            MLog.e("PERMISOS", "NO_SOLICITADOS");
        }
    }

    public void requestPermission(String permission, OnPermissionResult onPermissionResult) {
        requestPermission(new String[]{permission}, onPermissionResult);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        OnPermissionResult result = requestedPermissions.get(requestCode);

        if (result != null) {

            List<Permission> grant = new ArrayList<>();
            List<Permission> deny = new ArrayList<>();

            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        grant.add(new Permission(permissions[i], true));
                    } else {
                        deny.add(new Permission(permissions[i], false));
                    }
                }
            }

            if (grant.size() == grantResults.length) {
                result.onSuccess(grant);
            } else {
                result.onFail(deny);
            }

        } else {
            MLog.e("LOG_PERMISSION", "requestCode not found: " + requestCode);
        }

    }

    public void addActivityResult(int requestCode, OnActivityResult activityResult) {
        this.resultActivity.put(requestCode, activityResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        OnActivityResult onActivityResult = this.resultActivity.get(requestCode);

        if (onActivityResult != null) {
            onActivityResult.onResult(resultCode, data);
        }

    }

    public void initNavigation(int toolbarId, int drawerLayoutId, int navigationViewId, NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener) {

        if (getUi() != null) {
            Toolbar toolbar = getUi().getToolbar(toolbarId);
            DrawerLayout drawerLayout = getUi().getDrawerLayout(drawerLayoutId);
            NavigationView navigationView = getUi().getNavigationView(navigationViewId);
            setDrawer(drawerLayout);
            setToolbar(toolbar);

            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        }

    }

    public void customMenuIcon(int resId, final int gravity) {

        if (getDrawer() != null) {

            toolbar.setNavigationIcon(null);
            customMenu = getUi().getImageView(resId);

            if (customMenu != null) {

                customMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getDrawer().isDrawerVisible(gravity)) {
                            getDrawer().closeDrawer(gravity);
                        } else {
                            getDrawer().openDrawer(gravity);
                        }
                    }
                });

            }

        }

    }

    public void menuActivatedTrick(boolean show, View.OnClickListener event){

        if(getDrawer() != null) {
            toolbar.setNavigationIcon(null);
            getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            if(show){
                customMenu.setVisibility(View.VISIBLE);
            }
            else {
                customMenu.setVisibility(View.GONE);
            }

            customMenu.setOnClickListener(event);
        }

    }

    public void menuActivated(boolean show) {

        if (getDrawer() != null) {

            if (show) {
                if (customMenu == null) {
                    DrawerArrowDrawable menuIcon = new DrawerArrowDrawable(this);
                    menuIcon.setColor(getResources().getColor(android.R.color.white));
                    toolbar.setNavigationIcon(menuIcon);
                } else {
                    customMenu.setVisibility(View.VISIBLE);
                }
                getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                if (customMenu == null) {
                    toolbar.setNavigationIcon(null);
                } else {
                    customMenu.setVisibility(View.GONE);
                }
                getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

        }

    }

    public void hideKeyboard() {
        View viewCurrent = getCurrentFocus();
        if (viewCurrent != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewCurrent.getWindowToken(), 0);
        }
    }

    public void showKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            MLog.e("keybord", "view null");
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public File bitmapToFile(Bitmap image, String name) {

        File file = new File(getCacheDir(), name);

        try {

            file.createNewFile();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            byte[] bitmapData = outputStream.toByteArray();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            file = null;
        }

        return file;
    }

    public String getUnique(){

        StringBuilder sb = new StringBuilder();

        String androidId = getMacAddrV2();

        if (androidId == null || (androidId != null && androidId.isEmpty())) {
            androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        sb.append(Build.MANUFACTURER);
        sb.append("-");
        sb.append(Build.MODEL);
        sb.append("-");
        sb.append(Build.SERIAL);
        sb.append("-");
        sb.append(androidId);

        String unique = sb.toString();

        MLog.e("mac", unique);
        //unique = getHash(unique);

        return unique;
    }

    public static String getHash(String str) {
        MessageDigest digest = null;
        byte[] input = null;

        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            input = digest.digest(str.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convertToHex(input);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public String getUUID() {

        String androidId = "";

        /*androidId = getMacAddrV2();

        if (androidId == null || (androidId != null && androidId.isEmpty())) {
            androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }*/

        androidId = getUnique();
        return androidId;
    }

    public String getMacAddrV2() {

        try {

            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }

        } catch (Exception ex) {
        }

        return "";
    }

    public void printHashKey(Context pContext) {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                MLog.e("hashKey", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            MLog.e("hashKey", "printHashKey()" + e.getMessage());
        } catch (Exception e) {
            MLog.e("hashKey", "printHashKey()" + e.getMessage());
        }

    }

    public void toast(String text, int time){
        text = patchTraslate(text);
        Toast.makeText(getSelf(), text, time).show();
    }

    private String patchTraslate(String text){

        if(text != null && text.contains("Unable to")){
            text = "Error: Revisa tu conexión a internet.";
        }

        return text;
    }


    public String getVersionCode(){

        String version = "0";

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
        }

        return version;

    }

    public String getVersionName(){

        String version = "0";

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName+"";
        } catch (PackageManager.NameNotFoundException e) {
        }

        return version;

    }

}
