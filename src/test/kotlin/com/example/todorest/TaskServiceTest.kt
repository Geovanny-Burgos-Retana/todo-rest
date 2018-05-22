package com.todo

import com.example.todorest.Task
import com.example.todorest.TaskRepository
import com.example.todorest.TaskService
import org.junit.Test
import org.mockito.InjectMocks

import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TodoServiceTest {

    @Mock
    lateinit var todoRepository: TaskRepository;

    @InjectMocks
    lateinit var todoService : TaskService

    @Test
    fun `get all ToDos`() {

        val task1 = Task(1, Date(), "Tech Talk", "Yeah!")
        val task2 = Task(2, Date(), "Dinner", "With Friends")

        given(todoRepository.findAll()).willReturn(listOf(task1, task2))

        val todos = this.todoService.getTasks()

        verify(todoRepository, times(1)).findAll()

        assertThat(todos).isNotNull
        assertThat(todos).hasSize(2)
    }

}