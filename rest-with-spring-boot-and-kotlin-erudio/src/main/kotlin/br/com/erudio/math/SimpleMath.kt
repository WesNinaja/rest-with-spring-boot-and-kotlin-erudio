package br.com.erudio.math

import kotlin.math.sqrt

class SimpleMath {

    fun sum(numberOne: Double, numberTwo: Double) : Double = numberOne + numberTwo

    fun sub(numberOne: Double, numberTwo: Double) : Double = numberOne - numberTwo

    fun mult(numberOne: Double, numberTwo: Double) : Double = numberOne * numberTwo

    fun div(numberOne: Double, numberTwo: Double) : Double = numberOne / numberTwo

    fun mean(numberOne: Double, numberTwo: Double) : Double = (numberOne + numberTwo) / 2

    fun squareRoot(number: Double) : Double = sqrt(number)

}