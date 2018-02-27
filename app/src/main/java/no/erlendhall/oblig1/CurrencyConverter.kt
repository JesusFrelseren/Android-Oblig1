package no.erlendhall.oblig1

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/*
* Will be used in CalcReiseActivity as a fragment and functions as a currency converter
 */
class CurrencyManagerFragment : Fragment() {
    companion object {

        @JvmStatic
    fun newInstance() : CurrencyManagerFragment {
            return CurrencyManagerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
