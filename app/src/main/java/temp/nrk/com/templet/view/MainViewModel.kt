package temp.nrk.com.templet.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.reactivex.functions.Consumer
import temp.nrk.com.templet.model.Website
import temp.nrk.com.templet.repository.Repository

class MainViewModel(application: Application) :  AndroidViewModel(application){
    val repo:Repository = Repository(application)

    fun getDataList(onError: Consumer<Throwable>): LiveData<List<Website>> {
        return repo.getWebList(onError = onError)
    }
}