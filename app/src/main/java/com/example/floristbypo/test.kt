package com.example.floristbypo

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime



    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    @RequiresApi(Build.VERSION_CODES.O)
    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int?> {
         // val orderLines=OrderLine("9872", "Pencil", 3, 3.00)
        //val order=Order("554","2017-03-25T10:35:20",listOf(orderLines))
        val rtnMap :MutableMap<DayOfWeek,Int?> = mutableMapOf()
        for(i in DayOfWeek.values().indices)
        {
            rtnMap.put(DayOfWeek.values()[i],orders.find{x->x.creationDate.dayOfWeek== DayOfWeek.values()[i]}?.orderLines?.sumBy{ y->y.quantity})
        }
        return rtnMap
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun main(args: Array<String>)
    {
        val orderList= mutableListOf<Order>()
        orderList.add(Order(554, LocalDateTime.parse("2020-02-15T10:35:20"),
            listOf(OrderLine(9872, "Pencil", 3, 3.0.toBigDecimal())
            )))
        orderList.add(Order(555, LocalDateTime.parse("2020-02-15T11:35:20"),
            listOf(OrderLine(9872, "Pencil", 2, 3.0.toBigDecimal()),
                OrderLine(1746, "Eraser", 1, 1.0.toBigDecimal())
            )))
        orderList.add(Order(553, LocalDateTime.parse("2020-02-17T14:53:12"),
            listOf(OrderLine(5723, "Pen", 4, 4.22.toBigDecimal()),
                OrderLine(9872, "Pencil", 3, 3.12.toBigDecimal()),
                OrderLine(3433, "Erasers Set", 1, 6.15.toBigDecimal())
            )))

        orderList.add(Order(431, LocalDateTime.parse("2020-02-17T15:53:12"),
            listOf(OrderLine(5723, "Pen", 7, 4.22.toBigDecimal()),
                OrderLine(3433, "Erasers Set", 2, 6.15.toBigDecimal())
            )))
        orderList.add(Order(690, LocalDateTime.parse("2020-02-16T11:14:00"),
            listOf(OrderLine(9872, "Pencil", 4, 3.12.toBigDecimal()),
                OrderLine(4098, "Marker", 5, 4.50.toBigDecimal())
            )))

        val orderMap= totalDailySales(orderList)
        for(i in DayOfWeek.values().indices)
        println("${DayOfWeek.values()[i]} :${orderMap.getValue(DayOfWeek.values()[i])}")

    }
