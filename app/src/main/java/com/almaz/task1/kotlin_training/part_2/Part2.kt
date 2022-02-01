package com.almaz.task1.kotlin_training.part_2

import android.util.Log
import com.almaz.task1.kotlin_training.part2.Type

const val TAG = "MAIN_ACTIVITY_PART_2"
const val ADULT_AGE = 18

object Part2 {
    fun run() {
        /* 3
            Создать объект класса User, вывести в лог startTime данного юзера, после вызвать
            Thread.sleep(1000) и повторно вывести в лог startTime.
         */
        val person = User(0, "Ivan", 20, Type.FULL)
        Log.d(TAG, person.startTime)
        Thread.sleep(1000)
        Log.d(TAG, person.startTime)

        /* 4
            Создать список пользователей, содержащий в себе один объект класса User. Используя
            функцию apply, добавить ещё несколько объектов класса User в список пользователей.
         */
        val usersList = mutableListOf(person)
        usersList.apply {
            add(User(1, "Alexander", 30, Type.DEMO))
            add(User(2, "Nikolay", 40, Type.FULL))
            add(User(3, "Alexey", 17, Type.FULL))
        }

        /* 5
            Получить список пользователей, у которых имеется полный доступ
            (поле type имеет значение FULL).
         */
        val usersWithFullAccess = usersList.filter { it.type == Type.DEMO }

        /* 6
            Преобразовать список User в список имен пользователей. Получить первый и последний
            элементы списка и вывести их в лог.
         */
        usersList.map { it.name }.also { Log.d(TAG, "${it.first()}, ${it.last()}") }

        for (user in usersList) {
            doAction(Action.Login(user))
        }
    }

    /* 12
       Реализовать метод doAction, принимающий экземпляр класса Action. В зависимости от
       переданного действия выводить в лог текст, к примеру “Auth started”. Для действия
       Login вызывать метод auth.
    */
    private fun doAction(action: Action) {
        val updateCache: () -> Unit = { Log.d(TAG, "Cache updated") }
        when (action) {
            is Action.Registration -> Log.d(TAG, "Authorization started")
            is Action.Logout -> Log.d(TAG, "Logout started")
            is Action.Login -> action.user.auth(updateCache)
        }
    }

    /* 9
        Реализовать inline функцию auth, принимающую в качестве параметра функцию updateCache.
        Функция updateCache должна выводить в лог информацию об обновлении кэша.
     */
    private inline fun User.auth(updateCache: () -> Unit) {
        try {
            verifyAge()
            Authorization.authSuccess()
            updateCache.invoke()
        } catch (e: Exception) {
            Authorization.authFailed()
        }
    }

    /* 7
        Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет,
        и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку.
     */
    private fun User.verifyAge() {
        when (age > ADULT_AGE) {
            true -> Log.d(TAG, "User permitted")
            false -> throw IllegalArgumentException("Access is denied. User under 18")
        }
    }

    /* 8
        Создать интерфейс AuthCallback с методами authSuccess, authFailed и реализовать
        анонимный объект данного интерфейса. В методах необходимо вывести в лог информацию
        о статусе авторизации.
     */
    object Authorization : AuthCallback {
        override fun authSuccess() {
            Log.d(TAG, "Authorization successfull")
        }

        override fun authFailed() {
            Log.d(TAG, "Authorization failed")
        }
    }
}
