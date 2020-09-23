package `in`.gogaga.mvvm

import `in`.gogaga.extras.DataListener
import `in`.gogaga.rest.PostData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class MyViewModel : ViewModel() {

    fun getPosts(dataListener: DataListener) : LiveData<PagedList<PostData>>{
        val myRepository = MyRepository()
        return myRepository.getPosts(dataListener)
    }

}