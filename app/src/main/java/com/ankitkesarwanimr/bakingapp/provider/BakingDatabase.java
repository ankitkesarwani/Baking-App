package com.ankitkesarwanimr.bakingapp.provider;

import com.ankitkesarwanimr.bakingapp.helper.Constants;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by ankitkesarwanimr on 8/17/18.
 */

@Database(fileName = Constants.DATABASE_NAME, version = BakingDatabase.VERSION)
class BakingDatabase {
    static final int VERSION = 1;

    @Table(BakingContract.class)
    static final String TABLE_NAME = "baking_ingredients";
}