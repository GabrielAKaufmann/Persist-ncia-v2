package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    private lateinit var database: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o banco de dados
        database = TaskDatabase.getDatabase(this)

        setContent {
            // Pegar o ViewModel
            val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(database.taskDao()))

            // Tela principal
            TaskListScreen(
                tasks = viewModel.tasks,
                onAddTask = { title, description -> viewModel.addTask(title, description) },
                onDeleteTask = { task -> viewModel.deleteTask(task) }
            )
        }
    }
}
