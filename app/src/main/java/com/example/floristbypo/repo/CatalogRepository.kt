package com.example.floristbypo.repo

import com.example.floristbypo.models.Catalog
import com.example.floristbypo.models.User

interface CatalogRepository {
    suspend fun getCatalog(): Catalog
}