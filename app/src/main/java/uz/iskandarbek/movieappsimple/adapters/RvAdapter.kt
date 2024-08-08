package uz.iskandarbek.movieappsimple.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.iskandarbek.movieappsimple.databinding.ItemRvBinding
import uz.iskandarbek.movieappsimple.models.User
import uz.iskandarbek.movieappsimple.ustils.MySharedPreference

class RvAdapter(var rvAction: RvAction, var list: ArrayList<User>) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User, position: Int) {
            itemRv.apply {
                name.text = user.name
                about.text = user.about
                data.text = user.data
                root.setOnClickListener {
                    rvAction.itemClick(user, position)
                }
                delete.setOnClickListener {
                    notifyItemRemoved(position)
                    list.removeAt(position)
                    MySharedPreference.list = list
                }
                edit.setOnClickListener {
                    rvAction.editClick(user, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        //   Har bitta Item ni yasalishi
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //       Nechta item yasay
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        //   Item qiymatlarini beruchi
        holder.onBind(list[position], position)
    }

    interface RvAction {
        fun itemClick(user: User, position: Int)
        fun editClick(user: User, position: Int)
    }
}