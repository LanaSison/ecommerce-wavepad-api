package com.example.wavepad

import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNav: BottomNavigationView

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: List<ProductDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        // Set up the product list
        productList = listOf(
            ProductDataClass("Product 1", "Author 1", "Genre 1", "$10", R.drawable.wavepadlogo),
            ProductDataClass("Product 2", "Author 2", "Genre 2", "$20", R.drawable.wavepadlogo),
            ProductDataClass("Product 3", "Author 3", "Genre 3", "$30", R.drawable.wavepadlogo),
            ProductDataClass("Product 4", "Author 4", "Genre 4", "$40", R.drawable.wavepadlogo),
            ProductDataClass("Product 5", "Author 5", "Genre 5", "$50", R.drawable.wavepadlogo),
            ProductDataClass("Product 6", "Author 6", "Genre 6", "$60", R.drawable.wavepadlogo),
            ProductDataClass("Product 7", "Author 7", "Genre 7", "$70", R.drawable.wavepadlogo),
            ProductDataClass("Product 8", "Author 8", "Genre 8", "$80", R.drawable.wavepadlogo),
            ProductDataClass("Product 9", "Author 9", "Genre 9", "$90", R.drawable.wavepadlogo)
        )

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize ProductAdapter with the productList and item click listener
        productAdapter = ProductAdapter(productList) { product ->
            val intent = Intent(this, ProductFullDetail::class.java)
            intent.putExtra("PRODUCT", product)
            startActivity(intent)
        }

        // Set ProductAdapter to RecyclerView
        recyclerView.adapter = productAdapter

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        bottomNav = findViewById(R.id.bottomNavigationView)

        // Handle bottom navigation item clicks
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Handle bottom navigation item click for home
                    true
                }
                R.id.voucher -> {
                    startNewActivity(VoucherPage::class.java)
                    true
                }
                R.id.chat -> {
                    startNewActivity(ChatPage::class.java)
                    true
                }
                R.id.account -> {
                    startNewActivity(AccountPage::class.java)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Handle search action
                true
            }
            R.id.action_cart -> {
                startNewActivity(CartPage::class.java)
                true
            }
            R.id.menu -> {
                // Handle menu action (open drawer)
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
