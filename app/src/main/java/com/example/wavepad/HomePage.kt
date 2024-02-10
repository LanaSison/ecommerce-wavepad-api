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
import com.example.wavepad.databinding.HomePageBinding
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

        productList = listOf(
            ProductDataClass("Product 1", "$10", R.drawable.wavepadlogo, "Description of Product 1"),
            ProductDataClass("Product 2", "$20", R.drawable.wavepadlogo, "Description of Product 2"),
            ProductDataClass("Product 3", "$30", R.drawable.wavepadlogo, "Description of Product 3"),
            ProductDataClass("Product 4", "$40", R.drawable.wavepadlogo, "Description of Product 4"),
            ProductDataClass("Product 5", "$50", R.drawable.wavepadlogo, "Description of Product 5"),
            ProductDataClass("Product 6", "$60", R.drawable.wavepadlogo, "Description of Product 6"),
            ProductDataClass("Product 7", "$70", R.drawable.wavepadlogo, "Description of Product 7"),
            ProductDataClass("Product 8", "$80", R.drawable.wavepadlogo, "Description of Product 8"),
            ProductDataClass("Product 9", "$90", R.drawable.wavepadlogo, "Description of Product 9")
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize ProductAdapter with the productList
        productAdapter = ProductAdapter(productList)

        // Set ProductAdapter to RecyclerView
        recyclerView.adapter = productAdapter

        drawerLayout = findViewById(R.id.drawer_layout)
        bottomNav = findViewById(R.id.bottomNavigationView)

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

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