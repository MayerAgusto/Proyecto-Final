package com.example.afinal.Model

class Suggest(var title: String , var image: String ,
                   var category: String ,var description: String ,
                   var expand: Boolean ){
    constructor(): this("","","","", false)
}