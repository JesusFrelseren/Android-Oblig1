package no.erlendhall.oblig1

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class TotalCostFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): TotalCostFragment {
            return TotalCostFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        val view: View = inflater.inflate(R.layout.fragment_calc_total, container, false)

        var items = resources.getStringArray(R.array.currencies)
        var currencyBases = resources.getStringArray(R.array.currency_bases)
        val strList = arrayOf<String>(*items)


        val adapter: ArrayAdapter<String> = ArrayAdapter(context,
                R.layout.support_simple_spinner_dropdown_item, strList)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        val spinner: Spinner = view.findViewById<Spinner>(R.id.cur_spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val cur = view.findViewById<Spinner>(R.id.cur_spinner).selectedItemPosition
                val total = view.findViewById<TextView>(R.id.lbl_sum).text

                updateCurrency(cur, currencyBases, total)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        val btnCalc = view.findViewById<Button>(R.id.btn_calc)
        btnCalc.setOnClickListener({

            val numDays = view.findViewById<EditText>(R.id.antall_dager)
            val ticket = view.findViewById<EditText>(R.id.place_ticket_cost)
            val exp = view.findViewById<EditText>(R.id.avg_expenditure)

            updateSum(numDays, ticket, exp)
        })

        return view

    }

    private fun updateSum(numDays: EditText, ticket: EditText, exp: EditText) {

        val numDaysS = numDays.text.toString()
        val ticketS = ticket.text.toString()
        val expS = exp.text.toString()
        val sum = (expS.toDouble() * numDaysS.toDouble()) + ticketS.toDouble()

        val txtSum = view.findViewById<TextView>(R.id.lbl_sum)
        txtSum.text = sum.toString()
    }

    private fun updateCurrency(cur: Int, currencyBases: Array<String>, total: CharSequence) {
        val txtSum = view.findViewById<TextView>(R.id.lbl_sum)
        val currency = resources.getStringArray(R.array.currencies)
        var previousBase = currencyBases[cur]
        //Convert back to NOK
        if(previousBase === currencyBases[cur])

        var inNok = total.toString().toDouble() / previousBase.toDouble()

        val sum = inNok * currencyBases[cur].toDouble()

        txtSum.text = sum.toString()





}


