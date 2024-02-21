package com.example.wavepad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePage : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fab: FloatingActionButton
    private lateinit var searchView: SearchView

    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<ProductDataClass>
    private lateinit var productService: ProductService
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        productService = ProductService()

        productList = mutableListOf()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        productAdapter = ProductAdapter(productList,
            onItemClick = { product ->
                val intent = Intent(this, ProductFullDetail::class.java)
                intent.putExtra("PRODUCT", product)
                startActivity(intent)
            },
            onBuyButtonClick = { product ->
                val intent = Intent(this, ProductFullDetail::class.java)
                intent.putExtra("PRODUCT", product)
                startActivity(intent)
                Log.d("HomePage", "Clicked Buy Now for product: ${product.title}")
            }
        )

        recyclerView.adapter = productAdapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        bottomNav = findViewById(R.id.bottomNavigationView)
        fab = findViewById(R.id.fab)

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> true
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
        fab.setOnClickListener {
            startActivityForResult(Intent(this, UploadPage::class.java), UPLOAD_REQUEST_CODE)
        }

        fetchProductData()
    }
    //kukunin yung product sa api
    private fun fetchProductData() {
        productService.getProducts { products ->
            productList.clear()
            products?.let {
                productList.addAll(it)
                productAdapter.notifyDataSetChanged()
            }
        }
    }

    // Mag search ng product
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search products"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter(newText)
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                startNewActivity(CartPage::class.java)
                true
            }
            R.id.menu -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Kukunin nya yung product na inaupload mo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPLOAD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imagePath = data?.getStringExtra("imageFile")
            if (!imagePath.isNullOrEmpty()) {
                productList.add(ProductDataClass(
                    id = productList.size,
                    imageResource = R.drawable.wavepadlogo,
                    title = "New Product",
                    author = "New Author",
                    categories = "New Genre",
                    price = "$100",
                    description = "New Description"
                ))
                productAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    companion object {
        private const val UPLOAD_REQUEST_CODE = 123
    }
}
