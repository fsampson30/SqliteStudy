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
        db.close()
        return true
    }

    fun selectAll() : MutableList<Person> {
        val db = this.readableDatabase
        val cursor = db.query(DataBaseContract.StructureBase.TABLE_NAME, null, null, null, null, null, null)
        val persons = mutableListOf<Person>()
        with(cursor) {
            while (moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val active = cursor.getString(3).toBoolean()
                val currentPerson = Person(id,name, age,active)
                Log.i(TAG, "Person: ${currentPerson.showInformation()}")
                Log.i(TAG, "List: ${persons.toString()}")
                persons.add(currentPerson)
            }
        }
        cursor.close()
        db.close()
        return persons
    }


    fun deleteAll() {
        val db = this.writableDatabase
        val deletedRows = db.delete(DataBaseContract.StructureBase.TABLE_NAME, null, null)
        Log.i(TAG, deletedRows.toString())
        db.close()
    }
}