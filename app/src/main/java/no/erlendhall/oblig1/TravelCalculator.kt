package no.erlendhall.oblig1

class TravelCalculator {
    val bases = mapOf<String, Double>("RUB" to 67.2953, "AUD" to 0.165, "PEN" to 0.4105,
            "EUR" to 0.1032, "MXN" to 2.38)

    fun CalculateStay(cc: String, duration: Int): Double {

            val base = bases.getValue(cc)

            if (cc == "RUB") {
                return toNOK(300 + (duration * 46.0), base)
            }

            if (cc == "AUD") {
                return toNOK(78 + (duration * 20.0), base)
            }

            if (cc == "PEN") {
                return toNOK(183 + (duration * 12.0), base)
            }

            if (cc == "EUR") {
                return toNOK(38 + (duration * 14.0), base)
            }

            if (cc == "MXN") {
                return toNOK(303 + (duration * 31.0), base)
            }


        return 0.0
    }

    // Just to try out functions
    fun toNOK(input: Double,  base: Double): Double {
        return input / base
    }


}
