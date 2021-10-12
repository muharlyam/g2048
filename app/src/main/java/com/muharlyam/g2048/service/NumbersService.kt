package com.muharlyam.g2048.service

import com.muharlyam.g2048.model.Number

object NumbersService {

    private val numbers: Array<Array<Number>> = Array(4) { Array(4) { Number(0) } }
    private val preNumbers: Array<Array<Number>> = Array(4) { Array(4) { Number(0) } }
    private var preSize = 16;
    private var preScore = 0;

    private var size = 16
    private var score = 0;

    fun getScore(): Int {
        return score
    }

    private fun getNumbersList(numbersArray: Array<Array<Number>>): MutableList<String> {

        val numbersList: MutableList<String> = ArrayList()

        for (row in numbersArray) {
            for (cell in row) {
                if (cell.value == 0) {
                    numbersList.add("")
                } else {
                    numbersList.add(cell.value.toString())
                }
            }
        }

        return numbersList
    }

    private fun copyArray(from: Array<Array<Number>>, to: Array<Array<Number>>) {
        for(i in 0..3) {
            for(j in 0..3) {
                to[i][j].value = from[i][j].value
            }
        }
    }

    fun addNewNumber(): MutableList<String> {

        if (size > 0) {

            var x = (0..3).random()
            var y = (0..3).random()

            while (numbers[x][y].value != 0) {
                x = (0..3).random()
                y = (0..3).random()
            }

            if ((1..100).random() > 90) {
                numbers[x][y] = Number(4)
                size--
            } else {
                numbers[x][y] = Number(2)
                size--
            }
        }

        return getNumbersList(numbers)
    }

    fun reset(): MutableList<String> {
        for(i in 0..3) {
            for(j in 0..3) {
                numbers[i][j].value = 0
            }
        }
        size = 16
        score = 0

        addNewNumber()
        return getNumbersList(numbers)
    }

    fun cancel(): MutableList<String> {
        copyArray(preNumbers, numbers)
        size = preSize
        score = preScore
        return getNumbersList(numbers)
    }

    fun onSwipeTop(): MutableList<String> {
        copyArray(numbers, preNumbers)
        preScore = score
        preSize = size
        var isChange: Boolean = false
        for (rowIndex in 0..3) {
            var j: Int = -1
            for (cellIndex in 0..3) {
                if (cellIndex > 0 && numbers[cellIndex][rowIndex].value != 0) {
                    for (i in cellIndex - 1 downTo 0) {
                        if (j != i && numbers[i][rowIndex].value == numbers[cellIndex][rowIndex].value
                            && (numbers[i + 1][rowIndex].value == 0 || cellIndex == i + 1)
                        ) {
                            score += numbers[i][rowIndex].value * 2
                            numbers[i][rowIndex].value *= 2
                            numbers[cellIndex][rowIndex].value = 0
                            j = i
                            size++
                            isChange = true
                            break
                        } else if (numbers[i][rowIndex].value != 0 && numbers[i + 1][rowIndex].value == 0) {
                            numbers[i + 1][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            isChange = true
                            break
                        } else if (i == 0 && numbers[i][rowIndex].value == 0) {
                            numbers[i][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            isChange = true
                            break
                        }
                    }
                }
            }
        }
        return if (isChange) {
            addNewNumber()
        } else {
            getNumbersList(numbers)
        }
    }


    fun onSwipeBottom(): MutableList<String> {
        copyArray(numbers, preNumbers)
        preScore = score
        preSize = size
        var isChange: Boolean = false
        for (rowIndex in 0..3) {
            var j: Int = -1
            for (cellIndex in 3 downTo 0) {
                if (cellIndex < 3 && numbers[cellIndex][rowIndex].value != 0) {
                    for (i in (cellIndex + 1)..3) {
                        if (j != i && numbers[i][rowIndex].value == numbers[cellIndex][rowIndex].value
                            && (numbers[i - 1][rowIndex].value == 0 || cellIndex == i - 1)
                        ) {
                            score += numbers[i][rowIndex].value * 2
                            numbers[i][rowIndex].value *= 2
                            numbers[cellIndex][rowIndex].value = 0
                            j = i
                            isChange = true
                            size++
                            break
                        } else if (numbers[i][rowIndex].value != 0 && numbers[i - 1][rowIndex].value == 0) {
                            numbers[i - 1][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            isChange = true
                            break
                        } else if (i == 3 && numbers[3][rowIndex].value == 0) {
                            numbers[i][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            isChange = true
                            break
                        }
                    }
                }
            }
        }

        return if (isChange) {
            addNewNumber()
        } else {
            getNumbersList(numbers)
        }
    }

    fun onSwipeLeft(): MutableList<String> {
        copyArray(numbers, preNumbers)
        var isChange: Boolean = false
        preScore = score
        preSize = size
        for (rowIndex in 0..3) {
            var j: Int = -1
            for (cellIndex in 0..3) {
                if (cellIndex > 0 && numbers[rowIndex][cellIndex].value != 0) {
                    for (i in cellIndex - 1 downTo 0) {
                        if (j != i && numbers[rowIndex][i].value == numbers[rowIndex][cellIndex].value
                            && (numbers[rowIndex][i + 1].value == 0 || cellIndex == i + 1)
                        ) {
                            score += numbers[rowIndex][i].value * 2
                            numbers[rowIndex][i].value *= 2
                            numbers[rowIndex][cellIndex].value = 0
                            size++
                            j = i
                            isChange = true
                            break
                        } else if (numbers[rowIndex][i].value != 0 && numbers[rowIndex][i + 1].value == 0) {
                            numbers[rowIndex][i + 1].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            isChange = true
                            break
                        } else if (i == 0 && numbers[rowIndex][i].value == 0) {
                            numbers[rowIndex][i].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            isChange = true
                            break
                        }
                    }
                }
            }
        }

        return if (isChange) {
            addNewNumber()
        } else {
            getNumbersList(numbers)
        }
    }

    fun onSwipeRight(): MutableList<String> {
        copyArray(numbers, preNumbers)
        preScore = score
        preSize = size
        var isChange: Boolean = false
        for (rowIndex in 0..3) {
            var j: Int = -1
            for (cellIndex in 3 downTo 0) {
                if (cellIndex < 3 && numbers[rowIndex][cellIndex].value != 0) {
                    for (i in (cellIndex + 1)..3) {
                        if (j != i && numbers[rowIndex][i].value == numbers[rowIndex][cellIndex].value
                            && (numbers[rowIndex][i - 1].value == 0 || cellIndex == i - 1)
                        ) {
                            score += numbers[rowIndex][i].value * 2
                            numbers[rowIndex][i].value *= 2
                            numbers[rowIndex][cellIndex].value = 0
                            size++
                            j = i
                            isChange = true
                            break
                        } else if (numbers[rowIndex][i].value != 0 && numbers[rowIndex][i - 1].value == 0) {
                            numbers[rowIndex][i - 1].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            isChange = true
                            break
                        } else if (i == 3 && numbers[rowIndex][3].value == 0) {
                            numbers[rowIndex][i].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            isChange = true
                            break
                        }
                    }
                }

            }
        }

        return if (isChange) {
            addNewNumber()
        } else {
            getNumbersList(numbers)
        }
    }

}