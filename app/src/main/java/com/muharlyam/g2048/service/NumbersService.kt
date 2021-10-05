package com.muharlyam.g2048.service

import com.muharlyam.g2048.model.Number

object NumbersService {

    private val numbers: Array<Array<Number>> = Array(4) { Array(4) { Number(0) } }
    private var size = 16
    private var score = 0;

    fun getScore(): Int {
        return score
    }

    private fun getNumbersList(): MutableList<String> {

        val numbersList: MutableList<String> = ArrayList()

        for (row in numbers) {
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

    fun addNewNumbers(): MutableList<String> {
        var count = (1..2).random()

        while (count > 0 && size > 0) {

            var x = (0..3).random()
            var y = (0..3).random()

            while (numbers[x][y].value != 0) {
                x = (0..3).random()
                y = (0..3).random()
            }

            if ((0..1).random() > 0) {
                numbers[x][y] = Number(4)
                size--
            } else {
                numbers[x][y] = Number(2)
                size--
            }

            count--
        }

        return getNumbersList()
    }

    fun onSwipeTop(): MutableList<String> {
        for (rowIndex in 0..3) {
            for (cellIndex in 0..3) {
                if (cellIndex > 0 && numbers[cellIndex][rowIndex].value != 0) {
                    for (i in cellIndex - 1 downTo 0) {
                        if (numbers[i][rowIndex].value == numbers[cellIndex][rowIndex].value
                            && (numbers[i + 1][rowIndex].value == 0 || cellIndex == i + 1)
                        ) {
                            score += numbers[i][rowIndex].value * 2
                            numbers[i][rowIndex].value *= 2
                            numbers[cellIndex][rowIndex].value = 0
                            size++
                            break
                        } else if (numbers[i][rowIndex].value != 0 && numbers[i + 1][rowIndex].value == 0) {
                            numbers[i + 1][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            break
                        } else if (i == 0 && numbers[i][rowIndex].value == 0) {
                            numbers[i][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            break
                        }
                    }
                }
            }
        }
        return addNewNumbers()
    }


    fun onSwipeBottom(): MutableList<String> {

        for (rowIndex in 0..3) {
            for (cellIndex in 3 downTo 0) {
                if (cellIndex < 3 && numbers[cellIndex][rowIndex].value != 0) {
                    for (i in (cellIndex + 1)..3) {
                        if (numbers[i][rowIndex].value == numbers[cellIndex][rowIndex].value
                            && (numbers[i - 1][rowIndex].value == 0 || cellIndex == i - 1)
                        ) {
                            score += numbers[i][rowIndex].value * 2
                            numbers[i][rowIndex].value *= 2
                            numbers[cellIndex][rowIndex].value = 0
                            size++
                            break
                        } else if (numbers[i][rowIndex].value != 0 && numbers[i - 1][rowIndex].value == 0) {
                            numbers[i - 1][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            break
                        } else if (i == 3 && numbers[3][rowIndex].value == 0) {
                            numbers[i][rowIndex].value = numbers[cellIndex][rowIndex].value
                            numbers[cellIndex][rowIndex].value = 0
                            break
                        }
                    }
                }
            }
        }

        return addNewNumbers()
    }

    fun onSwipeLeft(): MutableList<String> {

        for (rowIndex in 0..3) {
            for (cellIndex in 0..3) {
                if (cellIndex > 0 && numbers[rowIndex][cellIndex].value != 0) {
                    for (i in cellIndex - 1 downTo 0) {
                        if (numbers[rowIndex][i].value == numbers[rowIndex][cellIndex].value
                            && (numbers[rowIndex][i + 1].value == 0 || cellIndex == i + 1)
                        ) {
                            score += numbers[rowIndex][i].value * 2
                            numbers[rowIndex][i].value *= 2
                            numbers[rowIndex][cellIndex].value = 0
                            size++
                            break
                        } else if (numbers[rowIndex][i].value != 0 && numbers[rowIndex][i + 1].value == 0) {
                            numbers[rowIndex][i + 1].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            break
                        } else if (i == 0 && numbers[rowIndex][i].value == 0) {
                            numbers[rowIndex][i].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            break
                        }
                    }
                }
            }
        }

        return addNewNumbers()
    }

    fun onSwipeRight(): MutableList<String> {

        for (rowIndex in 0..3) {
            for (cellIndex in 3 downTo 0) {
                if (cellIndex < 3 && numbers[rowIndex][cellIndex].value != 0) {
                    for (i in (cellIndex + 1)..3) {
                        if (numbers[rowIndex][i].value == numbers[rowIndex][cellIndex].value
                            && (numbers[rowIndex][i - 1].value == 0 || cellIndex == i - 1)
                        ) {
                            score += numbers[rowIndex][i].value * 2
                            numbers[rowIndex][i].value *= 2
                            numbers[rowIndex][cellIndex].value = 0
                            size++
                            break
                        } else if (numbers[rowIndex][i].value != 0 && numbers[rowIndex][i - 1].value == 0) {
                            numbers[rowIndex][i - 1].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            break
                        } else if (i == 3 && numbers[rowIndex][3].value == 0) {
                            numbers[rowIndex][i].value = numbers[rowIndex][cellIndex].value
                            numbers[rowIndex][cellIndex].value = 0
                            break
                        }
                    }
                }

            }
        }

        return addNewNumbers()
    }

}