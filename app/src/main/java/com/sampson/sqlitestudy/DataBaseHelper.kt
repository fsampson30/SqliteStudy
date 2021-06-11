package com.sampson.sqlitestudy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import kotlin.math.log

private const val TAG = "DataBaseHelper"

private const val SQL_CREATE_TABLE =
    "CREATE TABLE ${DataBaseContract.StructureBase.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${DataBaseContract.StructureBase.COLUMN_NAME} TEXT," +
            "${DataBaseContract.StructureBase.COLUMN_AGE} INT," +
            "${DataBaseContract.StructureBase.COLUMN_IS_ACTIVE} BOOLEAN)"

private const val SQL_DROP_TABLE =
    "DROP TABLE IF EXISTS ${DataBaseContract.StructureBase.TABLE_NAME}"


class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val db = this.readableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "person.db"
    }

    fun insertPerson(person: Person): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(DataBaseContract.StructureBase.COLUMN_NAME, person.name)
            put(DataBaseContract.StructureBase.COLUMN_AGE, person.age)
            put(DataBaseContract.StructureBase.COLUMN_IS_ACTIVE, person.isActive)
        }
        val newRowId = db.insert(DataBaseContract.StructureBase.TABLE_NAME, null, values)
        Log.i(TAG, newRowId.toString())
        return true
    }

    fun selectAll() {
        val cursor =
            db.query(DataBaseContract.StructureBase.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                Log.i(TAG, cursor.getString(0))
                Log.i(TAG, cursor.getString(1))
                Log.i(TAG, cursor.getString(2))
                Log.i(TAG, if (cursor.getString(3) == "1") "true" else "false")
            }
        }
    }


    fun deleteAll() {
        val deletedRows = db.delete(DataBaseContract.StructureBase.TABLE_NAME, null, null)
        Log.i(TAG, deletedRows.toString())
    }
}