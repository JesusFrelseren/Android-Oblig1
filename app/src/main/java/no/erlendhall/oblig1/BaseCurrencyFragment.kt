package no.erlendhall.oblig1

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text



class BaseCurrencyFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): TotalCostFragment {
            return TotalCostFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_base_currency, container, false)

        //Receive intent
        val extras = arguments.getString("currencycode")
        val test: TextView = view.findViewById(R.id.conversion)
        val out = StringBuilder()
        extras.forEach {
            out.append(it)
        }

        test.text = out.toString()
        return view


    }

}