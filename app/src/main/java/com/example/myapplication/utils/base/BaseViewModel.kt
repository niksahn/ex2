package com.example.myapplication.utils.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Базовая ViewModel
 *
 * Принимает типы состояния экрана и sealed class для событий экрана.
 * @property initialState Начальное состояние экрана
 * @see State
 * @see Event
 */
abstract class BaseViewModel<STATE : State, EVENT : Event>(initialState: STATE) : ViewModel() {


    private val _screenState = MutableStateFlow(initialState)

    /** Состояние экрана */
    val screenState = _screenState.asStateFlow()

    private val _event = MutableSharedFlow<EVENT>(extraBufferCapacity = 1)

    /** События для экрана */
    val event = _event.asSharedFlow()

    /** Текущее состояние экрана */
    protected val currentState: STATE
        get() = _screenState.value

    /* private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
         handleException(throwable)
     }*/

    /**
     * Обновить состояние экрана
     * @param block Функция для создания нового состояния при помощи предыдущего
     */
    fun updateState(block: (STATE) -> STATE) =
        _screenState.update(block)

    /**
     * Отправить событие на экран
     * @param event Событие для экрана
     */
    suspend fun sendEvent(event: EVENT) =
        _event.emit(event)

    /**
     * Отправить событие на экран и получить результат отправки
     * @param event Событие для экрана
     * @return Отправлено ли значение?
     */
    fun trySendEvent(event: EVENT) =
        _event.tryEmit(event)


    /**
     * Запуск корутины в скопе viewModel с обработкой ошибок и настроенным контекстом выполнения
     * @param block Код корутины
     * @see handleException
     */
    protected fun launchViewModelScope(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            context = SupervisorJob() + Dispatchers.IO,
            block = block
        )

    /**
     * Запуск корутины с получением реззультата в скопе viewModel с обработкой ошибок и настроенным
     * контекстом выполнения
     * @param block Код корутины
     * @see handleException
     */
    protected fun asyncViewModelScope(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.async(
            context = SupervisorJob() + Dispatchers.IO,
            block = block
        )

    /**
     * Метод для обработки исключений, которые произошли при исполнении корутин
     *
     * Дефолтная реализация печатает исключение в лог и отправляет в Crashlytics
     * @param throwable [Throwable]
     */
    /* protected open fun handleException(throwable: Throwable) {
         logger.error(throwable)
     }*/

    /**
     * Подписка на Flow в рамках жизни viewModel с обработкой ошибок и настроенным
     * контекстом выполнения
     * @param oomplete Вызов при завершении
     * @param onStart Вызов при старте
     * @param onCnEach Вызов для каждого элемента
     * @see handleException
     */
    protected fun <T> Flow<T>.subscribe(
        onStart: suspend FlowCollector<T>.() -> Unit = {},
        onComplete: suspend FlowCollector<T>.(Throwable?) -> Unit = {},
        onEach: suspend (T) -> Unit
    ) =
        this.onStart(onStart)
            .onEach(onEach)
            .onCompletion(onComplete)
            .flowOn(SupervisorJob() + Dispatchers.IO)
            .launchIn(viewModelScope)

}