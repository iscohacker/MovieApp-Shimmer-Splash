package uz.iskandarbek.movieappsimple

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.iskandarbek.movieappsimple.databinding.ActivityShowBinding
import uz.iskandarbek.movieappsimple.ustils.MySharedPreference

class ShowActivity : AppCompatActivity() {
    private val binding by lazy { ActivityShowBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.close.setOnClickListener {
            finish()
        }

        val keyPosition = intent.getIntExtra("p", 0)

        binding.name.text = MySharedPreference.list[keyPosition].name
        binding.authors.text = MySharedPreference.list[keyPosition].authors
        binding.about.text = MySharedPreference.list[keyPosition].about
        binding.data.text = MySharedPreference.list[keyPosition].data

    }
}