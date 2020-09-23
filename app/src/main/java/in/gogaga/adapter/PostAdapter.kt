package `in`.gogaga.adapter

import `in`.gogaga.R
import `in`.gogaga.diffCallback
import `in`.gogaga.rest.PostData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    private var context: Context
) : PagedListAdapter<PostData,PostAdapter.PostVH>(diffCallback) {

    class PostVH(view : View) : RecyclerView.ViewHolder(view){
        val post : TextView = view.findViewById(R.id.post)
        val title : TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val view = LayoutInflater.from(context).inflate(R.layout.element_posts,parent,false)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val currentItem = getItem(position)!!
        holder.post.text = currentItem.body
        holder.title.text = ("${position+ 1}.) ${currentItem.title}")
    }

}