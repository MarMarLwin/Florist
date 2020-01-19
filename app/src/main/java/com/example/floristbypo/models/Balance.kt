package com.example.floristbypo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.floristbypo.dataaccess.AppDatabase
import java.sql.Date
@Entity(tableName="Balance")
data class Balance (
    @PrimaryKey(autoGenerate = true)
        var CurrencyID:Int,
        var CurrencyUnit:Int,
        var Qty:Int,
        var EffectiveDate:Date
    )
