package `in`.gogaga.pagination

import `in`.gogaga.extras.DataListener
import `in`.gogaga.rest.Coroutines
import `in`.gogaga.rest.PostData
import `in`.gogaga.rest.RetrofitClient
import androidx.paging.PageKeyedDataSource

class PostsDataSource(
    private var dataListener: DataListener
) : PageKeyedDataSource<Int,PostData>() {

    private val api = RetrofitClient.getInstance().api
    private var pageNumber : Int = 0
    private var isFinished : Boolean = false

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PostData>
    ) {
        Coroutines.io {
            val response = api.getPosts()
            if (response.isSuccessful){
                val body = response.body()!!
                val list : ArrayList<PostData> = ArrayList()
                for (i in (pageNumber*20)..((pageNumber+1)*20)){
                    if (body.size > i){
                        list.add(body[i])
                    }else{
                        isFinished = true
                        dataListener.dataIsFinished()
                        break
                    }
                }
                pageNumber ++
                callback.onResult(list,null,pageNumber)
            }else{
                dataListener.errorLoading()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PostData>) {
        if (!isFinished){
            Coroutines.io {
                val response = api.getPosts()
                if (response.isSuccessful){
                    val body = response.body()!!
                    val list : ArrayList<PostData> = ArrayList()
                    for (i in (pageNumber*20)..((pageNumber+1)*20)){
                        if (body.size > i){
                            list.add(body[i])
                        }else{
                            isFinished = true
                            dataListener.dataIsFinished()
                            break
                        }
                    }
                    pageNumber ++
                    callback.onResult(list,pageNumber)
                }
            }
        }else{
            dataListener.dataIsFinished()
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PostData>) {}
}