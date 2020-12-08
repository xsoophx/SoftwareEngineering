package main

class Query(filter: Any){

    fun connect(){
        //should connect to database

    }
    fun query(){
        // should query database :
        // if array.length > 1: database.findMany(filter)
        // else database.findOne(filter)
    }
    fun close(){
        //should close connection
    }
}