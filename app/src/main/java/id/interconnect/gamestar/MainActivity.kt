package id.interconnect.gamestar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.interconnect.gamestar.databinding.ActivityMainBinding
import id.interconnect.gamestar.favorite.FavoriteFragment
import id.interconnect.gamestar.home.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var fragmentMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(fragmentMainBinding.root)

        var fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_fragment, fragment)
            .commit()

        //test favorite fragment
        fragmentMainBinding.testFragmentFavorite.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.host_fragment, FavoriteFragment())
                .commit()
        }
        //test home fragment
        fragmentMainBinding.testFragmentHome.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.host_fragment, HomeFragment())
                .commit()
        }
    }
}