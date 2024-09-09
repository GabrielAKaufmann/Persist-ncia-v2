class MainViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    fun loadTasks() {
        viewModelScope.launch {
            tasks.clear()
            tasks.addAll(taskDao.getAllTasks())
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            taskDao.insert(Task(title = title, description = description))
            loadTasks() // Recarregar tarefas após inserção
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
            loadTasks() // Recarregar tarefas após exclusão
        }
    }
}
