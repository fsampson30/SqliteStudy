package com.sampson.sqlitestudy

data class Person(
    var id:  Int = 0,
    var name : String = "",
    var age: Int = 0,
    var isActive: Boolean = false
) {
    fun showInformation(): String{
        return "Id: $id, Name: $name, Age: $age, Active: $isActive"
    }
}
