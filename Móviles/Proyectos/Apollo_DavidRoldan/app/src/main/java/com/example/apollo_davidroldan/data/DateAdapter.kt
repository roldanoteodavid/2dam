package com.example.apollo_davidroldan.data

import java.text.SimpleDateFormat
import java.util.Date

class DateAdapter : CustomScalarAdapter<Date> {
    private val format = SimpleDateFormat("yyyy-MM-dd")

    override fun decode(value: CustomScalarValue): Date {
        return format.parse(value.value.toString()) ?: throw RuntimeException("Invalid date format")
    }

    override fun encode(value: Date): CustomScalarValue {
        return CustomScalarValue.String(format.format(value))
    }
}