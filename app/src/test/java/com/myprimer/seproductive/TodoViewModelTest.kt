import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.myprimer.seproductive.Database.TodoDao
import com.myprimer.seproductive.Modelo.LoginViewModelTest
import com.myprimer.seproductive.Modelo.Todo
import com.myprimer.seproductive.Modelo.TodoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class TodoViewModelTest {

    @get:Rule
    val instantExecutorRule =
        LoginViewModelTest.InstantTaskExecutorRule() // Para ejecutar LiveData en el hilo principal

    private val testDispatcher = TestCoroutineDispatcher() // Para probar coroutines

    // Falso DAO en memoria
    class InMemoryTodoDao : TodoDao {
        private val todos = mutableListOf<Todo>()

        override fun getAllTodo(): LiveData<List<Todo>> {
            return object : LiveData<List<Todo>>() {
                init {
                    value = todos
                }
            }
        }

        override suspend fun addTodo(todo: Todo) {
            todos.add(todo)
        }

        override suspend fun deleteTodo(id: Int) {
            todos.removeIf { it.id == id }
        }
    }

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoDao: InMemoryTodoDao

    @Before
    fun setUp() {
        // Inicializa el DAO en memoria
        todoDao = InMemoryTodoDao()

        // Inicializa el ViewModel
        todoViewModel = TodoViewModel().apply {
            // Usa el DAO en memoria
            this.todoDao = todoDao
        }
    }

    @Test
    fun testAddTodo() = runBlockingTest {
        // Definimos el título y creamos un nuevo Todo
        val todoTitle = "Test Todo"
        val todo = Todo(title = todoTitle, createdAt = Date())

        // Llamada al método addTodo
        todoViewModel.addTodo(todoTitle)

        // Verifica que el tamaño de la lista de todos sea 1
        assertEquals(1, todoDao.getAllTodo().value?.size)

        // Verifica que el título del primer Todo en la lista sea el esperado
        assertEquals(todoTitle, todoDao.getAllTodo().value?.get(0)?.title)
    }

    @Test
    fun testDeleteTodo() = runBlockingTest {
        // Añadimos un Todo
        val todoTitle = "Test Todo"
        val todo = Todo(title = todoTitle, createdAt = Date())
        todoViewModel.addTodo(todoTitle)

        // Verificamos que el Todo fue añadido
        assertEquals(1, todoDao.getAllTodo().value?.size)

        // Llamada al método deleteTodo con el id del primer Todo
        todoViewModel.deleteTodo(todoDao.getAllTodo().value?.get(0)?.id ?: -1)

        // Verifica que la lista de todos esté vacía
        assertEquals(0, todoDao.getAllTodo().value?.size)
    }

    @Test
    fun testTodoList() {
        // Inicializamos una lista vacía de todos
        val mockTodoList = listOf<Todo>()
        val observer = Observer<List<Todo>> {}
        todoViewModel.todoList.observeForever(observer)

        // Verifica que la lista esté vacía
        assertEquals(mockTodoList, todoViewModel.todoList.value)
    }
}
