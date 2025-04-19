package com.example.mygeniusgastroproyec

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mygeniusgastroproyec.databinding.ActivityMainBinding
import com.example.mygeniusgastroproyec.ui.auth.LoginActivity
import com.example.mygeniusgastroproyec.utils.SessionManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (!SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Aquí puedes agregar una acción personalizada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_favorite, R.id.nav_perfil, R.id.nav_agregar
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    AlertDialog.Builder(this)
                        .setTitle("Cerrar sesión")
                        .setMessage("¿Estás seguro que deseas cerrar sesión?")
                        .setPositiveButton("Sí") { _, _ ->
                            SessionManager.logout(this)
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                    true
                }

                else -> {
                    menuItem.isChecked = true
                    navController.navigate(menuItem.itemId)
                    drawerLayout.closeDrawers()
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
