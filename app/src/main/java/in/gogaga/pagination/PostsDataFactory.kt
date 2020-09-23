package `in`.gogaga.pagination

import `in`.gogaga.extras.DataListener
import `in`.gogaga.rest.PostData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class PostsDataFactory(
    private var dataListener: DataListener
) : DataSource.Factory<Int,PostData>() {

    private val itemLiveDataSource : MutableLiveData<PageKeyedDataSource<Int, PostData>> = MutableLiveData()

    override fun create(): DataSource<Int, PostData> {
        val postDataSource = PostsDataSource(dataListener)
        itemLiveDataSource.postValue(postDataSource)
        return postDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, PostData>> {
        return itemLiveDataSource
    }

}