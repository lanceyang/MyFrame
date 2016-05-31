package com.lance.myframe.model;

import org.xutils.db.annotation.Column;

import java.util.List;

/**
 * Created by lance on 16/5/11.
 */
public class BasicDbModel {

    @Column(name = "id", isId = true)
    private int id;

}
