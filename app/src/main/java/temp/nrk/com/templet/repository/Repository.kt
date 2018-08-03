package temp.nrk.com.templet.repository


import android.app.Application
import android.app.ListActivity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import temp.nrk.com.templet.Dependencies.DependencyRegistry
import temp.nrk.com.templet.api.Api
import temp.nrk.com.templet.db.AppDB
import temp.nrk.com.templet.model.Website

class Repository(application:Application) {

    init { DependencyRegistry.shared.inject(application,this) }

    // insert dependencies
    fun configureWith( db: AppDB,api: Api) { this.api = api; this.db =db }

    lateinit var db: AppDB
    lateinit var api: Api

    fun getWebList(onError:Consumer<Throwable>):LiveData<List<Website>>{
        var liveData = MutableLiveData<List<Website>>()
        api.getAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doAfterNext { liveData.postValue(it)}
                .doOnError { onError.accept(it) }
                .subscribe()
        return  liveData
    }

    fun storeDB(action: Action){
        Observable.fromCallable { action.run() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doAfterNext { Log.i("Repo","DB updated") }
                .doOnError { Log.e("Repo","DB update fail",it) }
                .subscribe()
    }

//    fun fetchFromNet(onError: Consumer<Throwable>, onCompleted: Action) {
//        api!!.allData
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doAfterNext { websites ->
//                    insertAll(websites)
//                    onCompleted.run()
//                }
//                .doOnError(onError)
//                .subscribe()
//    }



}
