package com.example.testapp;

import android.app.Application;

import com.example.testapp.entity.DaoMaster;
import com.example.testapp.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by razamd on 2/28/2017.
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

}
