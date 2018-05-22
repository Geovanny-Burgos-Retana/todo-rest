package com.example.todorest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SpringBootApplication
class TodoRestApplication

fun main(args: Array<String>) {
    runApplication<TodoRestApplication>(*args)
}


enum class TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    DONE
}

@Entity
data class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val created: Date? = null,
        val title: String? = null,
        val summary: String? = null,
        val status: TaskStatus? = TaskStatus.NOT_STARTED
) : Serializable

@Repository
interface TaskRepository : JpaRepository<Task, Long> {

}

@Service
class TaskService(val todoRepository : TaskRepository) {

    fun getTasks() : List<Task> {
        return this.todoRepository.findAll()
    }

}

@RestController
@RequestMapping("/tasks")
class TaskController(val todoService : TaskService) {

    @GetMapping("", "/")
    fun allTasks() : List<Task> {
        return this.todoService.getTasks()
    }

}