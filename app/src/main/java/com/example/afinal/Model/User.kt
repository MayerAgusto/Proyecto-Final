package com.example.afinal.Model

class User {
    private var name:String = ""
    private var lastName:String=""
    private var password:String = ""
    private var email:String=""
    constructor(){}
    constructor(name:String, lastName:String,email:String, password:String){
        this.name= name
        this.lastName = lastName
        this.email = email
        this.password = password
    }
    fun getName():String {
        return this.name
    }
    fun getLastName():String{
        return this.lastName
    }
    fun getPassword():String{
        return this.password
    }
    fun getEmail():String {
        return this.email
    }
    fun setName(name:String){this.name = name}
    fun setLastName(lastName:String){this.lastName=lastName}
    fun setPassword(password:String){this.password = password}
    fun setEmail(email: String){this.email = email}
}