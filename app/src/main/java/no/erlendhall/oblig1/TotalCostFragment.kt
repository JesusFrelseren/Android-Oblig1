package no.erlendhall.oblig1

import android.app.Fragment
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.util.*

/**
 * Created by Erlen on 27-Feb-18.
 */

class TotalCostFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() : TotalCostFragment {
            return TotalCostFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        val view: View = inflater.inflate(R.layout.fragment_calc_total, container, false)

        var items = resources.getStringArray(R.array.currencies)
        val strList = arrayOf<String>(*items)


        val adapter: ArrayAdapter<String> = ArrayAdapter(context,
                R.layout.support_simple_spinner_dropdown_item, strList)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)


        val spinner: Spinner = view.findViewById<Spinner>(R.id.cur_spinner)
        spinner.adapter = adapter

        return view
    }
}