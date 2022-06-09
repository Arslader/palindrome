package com.palindrome


fun main() {
    var gamerList: MutableList<Gamer> = mutableListOf()
    while (true) {

        println("Введите ваше имя")
        val gamerName: String? = readLine()

        println("Введите вашу фразу")
        val phrase: String? = readLine()

        checkPalindrome(phrase, gamerProvider(gamerName, gamerList))

        println("Список лидеров")
        gamerList.sortByDescending { it.counter }
        gamerList.forEach { println("${it.name} - ${it.counter}") }
    }
}

fun gamerProvider(gamerName: String?, gamerList: MutableList<Gamer>): Gamer {
    var gamer: Gamer? = gamerList.find { it.name == gamerName }
    if (gamer == null) {
        gamer = Gamer(gamerName)
        if (gamerList.size < 6) {
            gamerList.add(gamer)
        }
    }
    return gamer
}

fun checkPalindrome(phrase: String?, gamer: Gamer) {

    println("Ваше имя: ${gamer.name}")

    val replacedPhrase: String? = phrase?.replace("[\\\\W ]".toRegex(), "")?.toLowerCase()
    val reversedPhrase: String? = replacedPhrase?.reversed()

    if (replacedPhrase == reversedPhrase && !gamer.usedPhrases.contains(phrase)) {
        if (replacedPhrase != null) {
            gamer.usedPhrases.add(phrase)
            gamer.counter += replacedPhrase.length
            println("Ваша фраза: $phrase - палиндром")
            println("Ваш счёт ${gamer.counter}")
        }
    }
    else {
        println("Ваша фраза $phrase не является палиндромом или вы уже вводили такую фразу")
    }
}

