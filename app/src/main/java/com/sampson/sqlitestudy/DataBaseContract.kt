package com.sampson.sqlitestudy

import android.provider.BaseColumns

object DataBaseContract {

    object StructureBase : BaseColumns {
        const val TABLE_NAME = "Person"
        const val COLUMN_NAME = "Name"
        const val COLUMN_AGE = "Age"
        const val COLUMN_IS_ACTIVE = "IsActive"
    }
}