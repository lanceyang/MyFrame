package com.lance.myframe.db;

import com.lance.myframe.model.BasicDbModel;
import com.lance.myframe.common.Global;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by lance on 16/5/11.
 */
public class DBHelper {

    private static DbManager.DaoConfig dbConfig;

//
//    Parent test = db.selector(Parent.class).where("id", "in", new int[]{1, 3, 6}).findFirst();
//    long count = db.selector(Parent.class).where("name", "LIKE", "w%").and("age", ">", 32).count();
//    List<Parent> testList = db.selector(Parent.class).where("id", "between", new String[]{"1", "5"}).findAll();
//

//    DbModel

    static {
        dbConfig =new DbManager.DaoConfig().setDbName(Global.DB_NAME).setDbVersion(Global.DB_VERSION)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }

    public static DbManager getDbManager(Class<BasicDbModel> clz){
        try {
            x.getDb(dbConfig).selector(clz);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return x.getDb(dbConfig);
    }
}
