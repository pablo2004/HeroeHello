package com.mno.init.Core.Interface;

import java.util.List;

import com.mno.init.Core.Object.Permission;

/**
 * Created by pablo on 25/05/17.
 */

public interface OnPermissionResult {

    void onSuccess(List<Permission> grant);

    void onFail(List<Permission> deny);

}
