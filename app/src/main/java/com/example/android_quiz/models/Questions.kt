package com.example.android_quiz.models

class Question(label:String,varArray: Array<String>,correct:Int) {
    constructor() : this("Question", arrayOf("A","B","C","D"), 0)

    var Label = label
    var VarArray = varArray
    var Correct = correct
}