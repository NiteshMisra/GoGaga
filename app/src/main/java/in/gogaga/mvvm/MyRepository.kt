package `in`.gogaga.mvvm

import `in`.gogaga.extras.DataListener
import `in`.gogaga.pagination.PostsDataFactory
import `in`.gogaga.rest.PostData
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class MyRepository {

    fun getPosts(dataListener: DataListener) : LiveData<PagedList<PostData>>{
        val postsDataFactory = PostsDataFactory(dataListener)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(postsDataFactory, config).build()
    }

}