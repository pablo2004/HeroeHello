package com.mno.init.Core.Object;

/**
 * Created by pablo on 25/05/17.
 */

public class Permission {

    private String name;
    private boolean granted;

    public Permission (String name, boolean granted){
        setName(name);
        setGranted(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }

}
