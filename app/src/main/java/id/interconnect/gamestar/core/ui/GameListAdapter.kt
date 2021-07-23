package id.interconnect.gamestar.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.gamestar.R
import id.interconnect.gamestar.core.domain.model.Game
import id.interconnect.gamestar.databinding.ItemListBinding

class GameListAdapter : RecyclerView.Adapter<GameListAdapter.ListViewHolder>() {
    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newDataList: List<Game>?){
        if(newDataList != null){
            listData.clear()
            listData.addAll(newDataList)
            notifyDataSetChanged()
        }else{
            return
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Game){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .into(binding.itemImage)
                binding.itemTitle.text = data.name_original
                binding.itemPlatform.text = data.parent_platforms
                binding.itemRating.text = data.rating.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

}