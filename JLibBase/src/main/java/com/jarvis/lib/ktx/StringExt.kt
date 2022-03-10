package com.jarvis.lib.ktx

import java.util.regex.Pattern

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/11
 */

/**
 * unicode 转中文
 */
fun String.unicodeDecode(): String {
    var string = this
    val pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))")
    val matcher = pattern.matcher(string)
    var ch: Char
    while (matcher.find()) {
        ch = matcher.group(2).toInt(16).toChar()
        string = string.replace(matcher.group(1), ch.toString() + "")
    }
    return string
}