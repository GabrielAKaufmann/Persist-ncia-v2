class ThemeManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    val isDarkMode = mutableStateOf(isSystemInDarkMode(context))

    fun toggleTheme() {
        val darkModeEnabled = !isDarkMode.value
        isDarkMode.value = darkModeEnabled
        prefs.edit().putBoolean("dark_mode", darkModeEnabled).apply()
    }

    private fun isSystemInDarkMode(context: Context): Boolean {
        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return prefs.getBoolean("dark_mode", currentNightMode == Configuration.UI_MODE_NIGHT_YES)
    }
}
setContent {
    val themeManager = ThemeManager(this)
    val isDarkMode = themeManager.isDarkMode.value

    ToDoListTheme(darkTheme = isDarkMode) {
        // Sua interface
        TaskListScreen(
            tasks = viewModel.tasks,
            onAddTask = { title, description -> viewModel.addTask(title, description) },
            onDeleteTask = { task -> viewModel.deleteTask(task) },
            onToggleTheme = { themeManager.toggleTheme() } // Botão de troca de tema
        )
    }
}
