package com.example.dev.fretex;

import android.app.Activity;

/**
 * Created by dev on 15/04/2018.
 */

public class Util {
    private Activity activity;
    private int[] ids;

    public Util( Activity activity, int... ids ){
        this.activity = activity;
        this.ids = ids;
    }

    public void lockFields( boolean isToLock ){
        for( int id : ids ){
            setLockField( id, isToLock );
        }
    }

    private void setLockField( int fieldId, boolean isToLock ){
        activity.findViewById( fieldId ).setEnabled( !isToLock );
    }
}
