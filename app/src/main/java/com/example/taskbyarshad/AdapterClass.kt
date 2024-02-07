import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbyarshad.User
import com.example.taskbyarshad.databinding.ItemUserBinding



class AdapterClass(private val onDeleteClickListener: (User) -> Unit,
                   private val onUpdateClickListener: (User) -> Unit
) : ListAdapter<User, AdapterClass.ViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding ,  onDeleteClickListener , onUpdateClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }

    class ViewHolder(private val binding: ItemUserBinding ,
                     private val onDeleteClickListener: (User) -> Unit,
                     private val onUpdateClickListener: (User) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.userName.text = user.name
            binding.userCity.text = user.info
            binding.deleteBtn.setOnClickListener {
                onDeleteClickListener.invoke(user)

            }

            binding.edit.setOnClickListener {
                onUpdateClickListener.invoke(user)
            }

            binding.constraintLv.setOnClickListener {
               user.isExpandable = !user.isExpandable
                binding.userCity.visibility = if (user.isExpandable) View.VISIBLE else View.GONE
            }
        }

    }


}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name // Modify this based on your unique identifier
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
