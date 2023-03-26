package com.websocket.demo

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.util.Base64Utils

internal class BinaryTest {

    @Test
    fun positiveIntegerBinaryString() {
        val four = 4
        val ten = 10
        val sixteen = 16
        val twenty = 20
        println("$four to binary string : ${four.toString(2)}")
        println("$ten to binary string : ${ten.toString(2)}")
        println("$sixteen to binary string : ${sixteen.toString(2)}")
        println("$twenty to binary string : ${twenty.toString(2)}")

        println(four.binaryToString())
        println(ten.binaryToString())
        println(sixteen.binaryToString())
        println(twenty.binaryToString())

        Assertions.assertThat(four.binaryToString()).isEqualTo(four.toString(2))
        Assertions.assertThat(ten.binaryToString()).isEqualTo(ten.toString(2))
        Assertions.assertThat(sixteen.binaryToString()).isEqualTo(sixteen.toString(2))
        Assertions.assertThat(twenty.binaryToString()).isEqualTo(twenty.toString(2))
    }

    @Test
    fun negativeIntegerBinaryString() {
        val minusFour = -4
        val minusTen = -10
        val minusSixteen = -16
        val minusTwenty = -20

        println("$minusFour to binary string : ${minusFour.toString(2)}")
        println("$minusTen to binary string : ${minusTen.toString(2)}")
        println("$minusSixteen to binary string : ${minusSixteen.toString(2)}")
        println("$minusTwenty to binary string : ${minusTwenty.toString(2)}")
    }

    @Test
    fun decimalBinaryString() {
        val zeroDotEightSevenFive = 0.875
        val zeroDotSevenFive = 0.75
        val zeroDotFive = 0.5
        val zeroDotTwoFive = 0.25
        val zeroDotOneTwoFive = 0.125

        println("$zeroDotEightSevenFive to binary string : ${zeroDotEightSevenFive.decimalBinaryToString()}")
        println("$zeroDotSevenFive to binary string : ${zeroDotSevenFive.decimalBinaryToString()}")
        println("$zeroDotFive to binary string : ${zeroDotFive.decimalBinaryToString()}")
        println("$zeroDotTwoFive to binary string : ${zeroDotTwoFive.decimalBinaryToString()}")
        println("$zeroDotOneTwoFive to binary string : ${zeroDotOneTwoFive.decimalBinaryToString()}")

        Assertions.assertThat(zeroDotEightSevenFive.decimalBinaryToString()).isEqualTo("0.111")
        Assertions.assertThat(zeroDotSevenFive.decimalBinaryToString()).isEqualTo("0.11")
        Assertions.assertThat(zeroDotFive.decimalBinaryToString()).isEqualTo("0.1")
        Assertions.assertThat(zeroDotTwoFive.decimalBinaryToString()).isEqualTo("0.01")
        Assertions.assertThat(zeroDotOneTwoFive.decimalBinaryToString()).isEqualTo("0.001")
    }

    private fun Int.binaryToString(): String {
        var value = this
        var result = ""
        while (value > 0) {
            result = if (value.rem(2) != 0) "1$result"
            else "0$result"
            value = value.div(2)
        }

        return result
    }

    private fun Double.decimalBinaryToString(): String {
        val max = 32
        var i = 0
        val result = StringBuilder("0.")
        var value = this
        while (i < max && value.toString() != "1.0") {
            value = value.times(2)
            when {
                value < 1 -> result.append("0")
                value == 1.0 -> result.append("1")
                else -> {
                    result.append("1")
                    value = value.minus(1)
                }
            }
            i++
        }

        return result.toString()
    }


    @Test
    fun base64() {
        // given
        val s = "Man"

        // when
        val encode = String(Base64Utils.encode(s.toByteArray()))

        // then
        println(encode)
    }

    @Test
    fun my() {
        // given
        val s = "Man"

        // when
        MyBase64Encoder.encode(s.toByteArray())

        // then
    }

    @Test
    fun ndg() {
        // given
        val ndg = "남동길"

        // when
        val encode = String(Base64Utils.encode(ndg.toByteArray()))

        // then
        println(encode)
    }

}

class MyBase64Encoder {
    companion object {
        private val toBase64 = charArrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        )

        fun encode(src: ByteArray) {
            for (b in src) {
                println(b)
            }
        }
    }
}