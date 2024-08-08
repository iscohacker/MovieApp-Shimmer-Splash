package uz.iskandarbek.movieappsimple

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.iskandarbek.movieappsimple.adapters.RvAdapter
import uz.iskandarbek.movieappsimple.databinding.ActivityAddBinding
import uz.iskandarbek.movieappsimple.models.User
import uz.iskandarbek.movieappsimple.ustils.MySharedPreference

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cancel.setOnClickListener { finish() }
        val keyFunction = intent.getStringExtra("keyFunction")
        val keyPosition = intent.getIntExtra("keyPosition", 0)

        if (keyFunction == "edit") {
            binding.name.setText(MySharedPreference.list[keyPosition].name)
            binding.authors.setText(MySharedPreference.list[keyPosition].authors)
            binding.about.setText(MySharedPreference.list[keyPosition].about)
            binding.data.setText(MySharedPreference.list[keyPosition].data)
        }

        binding.save.setOnClickListener {
            if (keyFunction == "add") {
                addMovie()
            } else if (keyFunction == "edit") {
                editMovie(keyPosition)
            }
        }
    }

    private fun addMovie() {
        MySharedPreference.init(this)
        val list = MySharedPreference.list
        binding.apply {
            if (name.text.isNotBlank() && authors.text.isNotBlank() && about.text.isNotBlank() && data.text.isNotBlank()) {
                val user = User(
                    name.text.toString(),
                    about.text.toString(),
                    authors.text.toString(),
                    data.text.toString()
                )
                list.add(user)
                MySharedPreference.list = list
                finish()
            } else {
                Toast.makeText(
                    this@AddActivity,
                    "Ma'lumotlar to'liq kiritilmagan!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun editMovie(position: Int) {
        MySharedPreference.init(this)
        val list = MySharedPreference.list
        binding.apply {
            if (name.text.isNotBlank() && authors.text.isNotBlank() && about.text.isNotBlank() && data.text.isNotBlank()) {
                val user = User(
                    name.text.toString(),
                    about.text.toString(),
                    authors.text.toString(),
                    data.text.toString()
                )
                list[position] = user
                MySharedPreference.list = list
                finish()
            } else {
                Toast.makeText(
                    this@AddActivity,
                    "Ma'lumotlar to'liq kiritilmagan!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}