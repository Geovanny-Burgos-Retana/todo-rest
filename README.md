TODO-REST
=========

## Pasos

1. Mostrar aplicacion, mostrar partes relevantes.
2. Ejecutar applicacion desde IntellJ con perfil `local`
3. Mostrar con `http :8080/tasks`, `http :8080/actuator`
4. Crear un repositorio en GitHub: `todo-rest`
5. Subir código a GitHub:
```bash
> git init .
> git add .
> git commit -m "commit inicial"
> git remote add origin https://github.com/martinicr/todo-rest.git
> git push -u origin master
```

6. Ir a Travis-CI
7. Agregar nuevo repositorio
8. Sincronizar con cuenta de GitHub de ser necesario
9. Dar acceso a repositorio `todo-rest`
10. Agregar texto en `README.md`, luego publicar en GitHub
11. Ver cómo el proyecto se construye
12. Crear nuevo branch `staging`: `git checkout -b staging`
13. `heroku login`
14. Crear nuevo proyecto: `heroku apps:create todo-staging --remote staging`
15. Agregar Base de Datos a proyecto
```bash
> heroku addons:create heroku-postgresql:hobby-dev -a todo-copy-staging
> heroku pg -a todo-copy-staging 
```
16. Deploy `git push stating master` 

17. Crear nuevo proyecto (production)
```bash
heroku apps:create todo-production --remote production
heroku addons:create heroku-postgresql:hobby-dev -a todo-production
git push production master
``` 

18. Deployment a Heroku via Travis-CI
19. Actualizar API Key `travis encrypt $(heroku auth:token) --add deploy.api_key`


```kotlin
@Service
class TodoService(val todoRepository : TodoRepository) {

    fun getTodos() : List<Todo> {
        return this.todoRepository.findAll()
    }

    fun addTask(todo: Todo) {
        this.todoRepository.save(todo)
    }

}

@RestController
@RequestMapping("/todos")
class TodoController(val taskService : TodoService) {

    @GetMapping("", "/")
    fun allTasks() : List<Task> {
        return this.todoService.getTasks()
    }

    @PostMapping("", "/")
    fun addTask(@RequestBody newTask : Task) : ResponseEntity<Task>{
        this.todoService.addTask(newTask)
        return ResponseEntity(HttpStatus.CREATED)
    }

}
```

20. Pipelines
```bash
> heroku pipelines:create -a todo-staging --stage staging
> heroku pipelines:add -a todo-production todo-pipeline --stage production
```