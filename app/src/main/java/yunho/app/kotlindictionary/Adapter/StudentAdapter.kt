package yunho.app.kotlindictionary.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yunho.app.kotlindictionary.Entity.Food
import yunho.app.kotlindictionary.Entity.Student
import yunho.app.kotlindictionary.R
import yunho.app.kotlindictionary.databinding.ActivityDatabasetestBinding
import yunho.app.kotlindictionary.databinding.ItemStudentBinding

class StudentAdapter(private val listener: (Student) -> Unit) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    var students = mutableListOf<Student>()
    inner class ViewHolder(private var binding: ItemStudentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Student){
            binding.age.text = item.name
            binding.name.text = item.name
            binding.major.text = item.major

            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student ,parent,false)
        val binding = ItemStudentBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}