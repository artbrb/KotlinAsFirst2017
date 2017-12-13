@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

import lesson8.task1.transliterate
import java.lang.reflect.Executable

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
val monthString = listOf<String>("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
        "августа", "сентября", "октября", "ноября", "декабря")

fun dateStrToDigit(str: String): String {
    val myRegex = """(\d{1,2}) ([а-я]{3,8}) (\d+)""".toRegex()
    val splitStr = str.split(" ")
    return if (str.matches(myRegex) && splitStr[1] in monthString) {
        val number = splitStr[0].toInt()
        val month = splitStr[1]
        val years = splitStr[2].toInt()
        String.format("%02d.%02d.%d", number, monthString.indexOf(month) + 1, years)
    } else ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val myRegex = """\d\d.\d\d.(\d+)""".toRegex()
    val splitDigital = digital.split(".")
    return if (digital.matches(myRegex) && !splitDigital[1].matches("""0+""".toRegex())) {
        val number = splitDigital[0].toInt()
        val month = monthString[splitDigital[1].toInt() - 1]
        val years = splitDigital[2].toInt()
        String.format("%d %s %d", number, month, years)
    } else ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val splitJump = jumps.split(" ")
    val regexNumeric = """\d+""".toRegex()
    val regexForJumps = """[-+%0-9 ]*""".toRegex()
    var maxJump = -1
    return if (!jumps.matches(regexForJumps) || !jumps.contains(regexNumeric)) {
        -1
    } else {
        for (i in 0 until splitJump.size - 1) {
            val conditionForNumber = splitJump[i].matches(regexNumeric) && splitJump[i].toInt() > maxJump
            val conditionForSign = "+" in splitJump[i + 1]
            if (conditionForNumber && conditionForSign) {
                maxJump = splitJump[i].toInt()
            }
        }
        maxJump
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = TODO()

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val splitStr = str.toLowerCase().split(" ")
    var index = 0
    if (splitStr.size == 1) {
        return -1
    }
    for (i in 0 until splitStr.size - 1) {
        if (splitStr[i] == splitStr[i + 1]) {
            return index
        } else {
            index += splitStr[i].length + 1
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val splitDescription = description.split("""((;)? )""".toRegex())
    var maxPrize = -1.0
    var maxPrizeName = ""
    val conditionForPrize = """\d+(\.\d+)?""".toRegex()
    for (i in 0 until splitDescription.size - 1 step 2) {
        if (splitDescription[i + 1].matches(conditionForPrize)) {
            if (splitDescription[i + 1].toDouble() > maxPrize) {
                maxPrize = splitDescription[i + 1].toDouble()
                maxPrizeName = splitDescription[i]
            }
        } else {
            return ""
        }
    }
    return maxPrizeName
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()



fun exams(examResults: List<String>, threshold: Double): List<String> {
    val results = mutableListOf<String>()
    var count = 0
    var numberExams = 0
    for (i in 0 until examResults.size) {
        var line = examResults[i]
        require(line matches Regex("""([А-Яа-я]|Ё|ё)* ([А-Яа-я]|Ё|ё)*[ ]+-([ ]*([А-Яа-я]|Ё|ё)*[ ]*[0-5][,]?)*"""))
        require(threshold in 3.0..5.0)
        var string = examResults[i].split(" ", "-", ",")
        string = string.filter { it != "" }
        if (string.size >= 4) {
            for (j in 3 until string.size step 2) {
                count += string[j].toInt()
                numberExams += 1
            }
            if (count / numberExams > threshold) results.add(string[0] + " " + string[1])
        }
    }
    return results
}


val alphabet = Regex("[A-Z]")
val ABC = Regex("[ABC]")
val DEF = Regex("[DEF]")
val GHI = Regex("[GHI]")
val JKL = Regex("[JKL]")
val MNO = Regex("[MNO]")
val PQRS = Regex("PQRS]")
val TUV = Regex("[TUV]")

fun numberDeCode(text: String): String {
    val phone = mutableListOf<Char>()
    for (i in 0 until text.length) {
        val take = text[i].toUpperCase()
        if (take.toString() matches alphabet) phone.add(deCode(take))
         else phone.add(text[i])
    }
    return phone.joinToString( "" )
}
fun deCode(code: Char):Char = when {
    code.toString() matches ABC -> '2'
    code.toString() matches DEF -> '3'
    code.toString() matches GHI -> '4'
    code.toString() matches JKL -> '5'
    code.toString() matches MNO -> '6'
    code.toString() matches PQRS -> '7'
    code.toString() matches TUV -> '8'
    else -> '9'
}


data class Bigramma(val str: String, var count: Int = 1)

fun bigr(text: String): List<Bigramma> {
    fun chk(ch: Char): Boolean = ch in 'a' until 'z' || ch in 'A' until 'Z' || ch in 'а' until 'я' || ch in 'А' until 'Я'
            || ch == 'ё' || ch == 'Ё'

    val res = mutableListOf<Bigramma>()
    for (i in 0 until text.length - 1) {
        var a = true
        if (chk(text[i]) && chk(text[i + 1])) {
            for (j in 0 until res.size)
                if (res[j].str == text[i].toString() + text[i + 1]) {
                    res[j].count++
                    a = false
                }
            if (a) res.add(Bigramma(text[i].toString() + text[i + 1]))
        }
    }
    return res
}