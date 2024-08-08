package uz.iskandarbek.movieappsimple

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uz.iskandarbek.movieappsimple.adapters.RvAdapter
import uz.iskandarbek.movieappsimple.databinding.ActivityMainBinding
import uz.iskandarbek.movieappsimple.models.User
import uz.iskandarbek.movieappsimple.ustils.MySharedPreference

class MainActivity : AppCompatActivity(), RvAdapter.RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())
        onResume()
        binding.add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("keyFunction", "add")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)
        val list = MySharedPreference.list
        binding.shimmerLayout.startShimmer()
        binding.rv.visibility = View.GONE
        handler.postDelayed(runnable, 2000)
        rvAdapter = RvAdapter(this, list)
        binding.rv.adapter = rvAdapter
    }

    override fun itemClick(user: User, position: Int) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("p", position)
        startActivity(intent)
    }

    override fun editClick(user: User, position: Int) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("keyFunction", "edit")
        intent.putExtra("keyPosition", position)
        startActivity(intent)
    }

    private val runnable = object : Runnable {
        override fun run() {
            binding.shimmerLayout.stopShimmer()
            binding.linear.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
            handler.postDelayed(this, 2000)
        }
    }
}