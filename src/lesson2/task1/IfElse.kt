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
        (age in 11..19) || (age in 111..119) -> "$age лет"
        (lastFigure  in 2..4) && (age !in 11..19) && (age !in 111..119) -> "$age года"
        (lastFigure  in 5..9) && (age !in 11..19) && (age !in 111..119) -> "$age лет"
        (lastFigure  == 0) && ( age !in 11..19) && (age !in 111..119) -> "$age лет"
        (lastFigure  == 1) && ( age !in 11..19) && (age !in 111..119) -> "$age год"
         else -> " "


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
    val s = (v1 * t1) + (v2 * t2) + (v3 * t3)
    val middleWay = s / 2
    val intervaL1 = v1 * t1
    val intervaL2 = v2 * t2
    val intervaL3 = v3 * t3
    // Рассматриваю три случая расположения середины пути: на промежутках v1 * t1, v2 * t2, v3 * t3
    return when {
        intervaL1 >= middleWay ->  middleWay / v1
        ((intervaL1 + intervaL2) >= middleWay) -> (middleWay - intervaL1) / v2 + t1
        (middleWay > (intervaL1 + intervaL2)) -> (intervaL3 - (s - middleWay)) / v3 + t2 + t1
        else -> Double.NaN
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
                       rookX2: Int, rookY2: Int): Int  //Сначала проверяю опасность отпервого , потом от второго , затем случай одновременной угрозы
{
    return when {
        (( kingX == rookX1 ) || ( kingY == rookY1 )) && (( kingX != rookX2 ) && ( kingY != rookY2 )) -> 1
        (( kingX == rookX2 ) || ( kingY == rookY2 )) && (( kingX != rookX1 ) && ( kingY != rookY1 )) -> 2
        (( kingX == rookX1 ) || ( kingY == rookY1 )) && (( kingX == rookX2 ) || ( kingY == rookY2 )) -> 3
        else -> 0
    }
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
                          rookX: Int, rookY: Int, bishopX: Int, bishopY: Int): Int =
        when {
            (( kingX == rookX ) || ( kingY == rookY )) && (abs( kingX - bishopX ) != abs( kingY - bishopY )) -> 1
            (( kingX != rookX ) && ( kingY != rookY )) && ( abs( kingX - bishopX ) == abs( kingY - bishopY )) -> 2
            (( kingX == rookX ) || ( kingY == rookY )) && ( abs( kingX - bishopX ) == abs( kingY - bishopY )) -> 3
            else -> 0
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
    val small = min ( min(a, b), c)
    val biG   = max ( max(a, b), c)
    val mid   = (a + b + c ) - (small + biG)
    val hypot = sqrt( mid * mid + small * small)
    return when {
        (hypot == biG) -> 1
        (( small + mid ) <= biG) -> -1
        (hypot > biG) -> 0
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        // случай, когда b левее d
        (( b <= d ) && ( a <= b ) && ( c <= b ) && ( a <= c )) -> ( b - c )
        (( b <= d ) && ( a <= b ) && ( c <= b ) && ( c <= a )) -> ( b - a )
        // случай, когда d левее b
        (( b >= d ) && ( a <= d ) && ( c <= d ) && ( a <= c )) ->  ( d - c )
        (( b >= d ) && ( a <= d ) && ( c <= d ) && ( c <= a )) -> ( d - a )

        else -> -1

    }
}