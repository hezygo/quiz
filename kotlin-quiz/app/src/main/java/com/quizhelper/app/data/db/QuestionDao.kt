package com.quizhelper.app.data.db

import androidx.room.*
import com.quizhelper.app.data.model.Question
import com.quizhelper.app.data.model.QuestionBankMeta
import com.quizhelper.app.data.model.WrongQuestion
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM questions ORDER BY id ASC")
    suspend fun getAllQuestionsList(): List<Question>

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Long): Question?

    @Query("SELECT * FROM questions WHERE type = :type ORDER BY id ASC")
    suspend fun getQuestionsByType(type: String): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<Question>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question): Long

    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int

    @Query("SELECT COUNT(*) FROM questions WHERE type = :type")
    suspend fun getQuestionCountByType(type: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBankMeta(meta: QuestionBankMeta)

    @Query("SELECT * FROM question_bank_meta WHERE id = 1")
    suspend fun getBankMeta(): QuestionBankMeta?

    @Query("SELECT * FROM question_bank_meta WHERE id = 1")
    fun getBankMetaFlow(): Flow<QuestionBankMeta?>

    @Query("DELETE FROM question_bank_meta")
    suspend fun deleteBankMeta()

    @Transaction
    suspend fun replaceAll(questions: List<Question>, meta: QuestionBankMeta) {
        deleteAllQuestions()
        insertQuestions(questions)
        deleteBankMeta()
        insertBankMeta(meta)
    }

    // ---- Wrong Questions ----

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWrongQuestion(wq: WrongQuestion)

    @Query("DELETE FROM wrong_questions WHERE question_id = :questionId")
    suspend fun deleteWrongQuestion(questionId: Long)

    @Query("SELECT * FROM wrong_questions ORDER BY last_wrong_time DESC")
    fun getAllWrongQuestionsFlow(): Flow<List<WrongQuestion>>

    @Query("SELECT q.* FROM questions q INNER JOIN wrong_questions wq ON q.id = wq.question_id")
    suspend fun getWrongQuestionsList(): List<Question>

    @Query("SELECT COUNT(*) FROM wrong_questions")
    suspend fun getWrongQuestionCount(): Int

    @Query("SELECT * FROM wrong_questions WHERE question_id = :questionId")
    suspend fun getWrongQuestionByQuestionId(questionId: Long): WrongQuestion?

    @Query("DELETE FROM wrong_questions")
    suspend fun deleteAllWrongQuestions()
}
