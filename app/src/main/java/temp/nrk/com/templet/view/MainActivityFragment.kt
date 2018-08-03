package temp.nrk.com.templet.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_main.*
import temp.nrk.com.templet.R

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let { it.getInt(ARG_COLUMN_COUNT) }

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataList(Consumer { showError(it) }).observe(this, Observer {
            textView?.text = "Result ${it?.size}"; Log.i("Result",it.toString())
        })
        textView
    }

    fun showError(error:Throwable){
        Toast.makeText(context,"Error $error",Toast.LENGTH_SHORT).show()
    }
}
