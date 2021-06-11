package com.sampson.sqlitestudy

data class Person(
    var id:  Int,
    var name : String,
    var age: Int,
    var isActive: Boolean
) {
    fun showInformation(): String{
        return "Id: $id, Name: $name, Age: $age, Active: $isActive"
    }
}
