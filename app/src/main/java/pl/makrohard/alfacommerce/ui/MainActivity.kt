package pl.makrohard.alfacommerce.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.api.ApiClient
import pl.makrohard.alfacommerce.model.Category
import pl.makrohard.alfacommerce.ui.main.ProductsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }

        val callback = object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "" + response.body()?.size,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Błąd API", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        }
        ApiClient.categoriesApi.index().enqueue(callback)
    }
}