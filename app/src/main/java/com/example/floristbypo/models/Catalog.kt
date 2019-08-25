package com.example.floristbypo.models
import java.sql.Blob

data class Catalog(
    var CatalogId:String="",
    var Name:String="",
    var Type:String?=null,
    var Color:String?=null,
    var Price:Double?=0.0,
    var AvailableQty:Double?=0.0,
    var Description:String?=null,
    var Photo: Blob?=null
)

