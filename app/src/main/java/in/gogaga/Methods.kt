package `in`.gogaga

import `in`.gogaga.rest.PostData
import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

val diffCallback: DiffUtil.ItemCallback<PostData> =
    object : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem == newItem
        }
    }