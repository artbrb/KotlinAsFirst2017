@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val lastFigure = age % 10
    return when {
        (lastFigure in 2..4) && (age % 100 !in 11..19) -> "$age года"
        (lastFigure in 5..9) || (lastFigure == 0) || (age % 100 in 11..19) -> "$age лет"
        else -> "$age год"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val intervaL1 = v1 * t1
    val intervaL2 = v2 * t2
    val intervaL3 = v3 * t3
    val s = intervaL1 + intervaL2 + intervaL3
    return when {
        intervaL1 >= s / 2 -> (s / 2) / v1
        ((intervaL1 + intervaL2) >= s / 2) -> (s / 2 - intervaL1) / v2 + t1
        else -> (intervaL3 - (s / 2)) / v3 + t2 + t1
    }
}





/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    var result = 0
    if (kingX == rookX1 || kingY == rookY1) {
        result++
    }
    if (kingX == rookX2 || kingY == rookY2) {
        result += 2
    }
    return result
}



/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int, bishopX: Int, bishopY: Int): Int {
    var result = 0
    if (kingX == rookX || kingY == rookY) {
        result++
    }
    if (abs(kingX - bishopX) == abs(kingY - bishopY)) {
        result += 2
    }
    return result
}


/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val small = minOf(a, b, c)
    val big = maxOf(a, b, c)
    val mid = a + b + c - (small + big)
    val hypot = sqrt(mid * mid + small * small)
    return when {
        hypot == big -> 1
        small + mid <= big -> -1
        hypot > big -> 0
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
        = when {
    (b <= d) && (c <= b) && (a <= c) -> (b - c)
    (b <= d) && (c <= b) && (c <= a) -> (b - a)
    (b >= d) && (a <= d) && (a <= c) -> (d - c)
    (b >= d) && (a <= d) && (c <= a) -> (d - a)
    else -> -1
}