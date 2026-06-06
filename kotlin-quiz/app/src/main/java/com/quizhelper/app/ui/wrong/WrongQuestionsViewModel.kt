package com.quizhelper.app.ui.wrong

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.quizhelper.app.data.model.Question
import com.quizhelper.app.data.repository.QuizRepository
import com.quizhelper.app.util.Logger
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WrongQuestionsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = QuizRepository(application)
    private val log = Logger.create("WrongQVM")

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            val list = repository.getWrongQuestionsList()
            _questions.value = list
            _count.value = list.size
        }
    }

    fun remove(questionId: Long) {
        viewModelScope.launch {
            repository.removeFromWrongQuestions(questionId)
            load()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAllWrongQuestions()
            load()
            log.i("错题集已清空")
        }
    }
}
