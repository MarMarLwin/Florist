package com.example.floristbypo.models
import androidx.room.Entity

@Entity(tableName="Catalog")
data class Catalog(
    var CatalogId:String="",
    var Name:String="",
    var Type:String?="",
    var Color:String?="",
    var Price:Double?=0.0,
    var AvailableQty:Double?=0.0,
    var Description:String?="",
    var Photo_URL: String?=""
)

