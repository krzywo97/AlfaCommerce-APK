package pl.makrohard.alfacommerce

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.repository.CategoriesRepository
import kotlin.test.assertTrue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var categoriesRepository: CategoriesRepository

    @Before
    fun setup() {
        val list = listOf(
            Category(1, "Hoodies"),
            Category(2, "T-shirts"),
            Category(3, "Caps"),
            Category(4, "Sneakers"),
        )

        categoriesRepository = mock {
            on { runBlocking { index() } } doReturn list
        }
    }

    @Test
    fun repository_getData() = runBlocking {
        val categoriesList = categoriesRepository.index()
        assertTrue(true, "xd")
    }
}