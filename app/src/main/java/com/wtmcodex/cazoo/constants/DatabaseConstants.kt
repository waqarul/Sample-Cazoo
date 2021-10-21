package com.wtmcodex.cazoo.constants

interface DatabaseConstants {
    companion object {
        const val DATABASE_NAME = "CazooProducts.db"
        const val TABLE_PRODUCT = "Product"
        const val QUERY_SELECT_PRODUCT = "SELECT * FROM $TABLE_PRODUCT"
        const val QUERY_SELECT_PRODUCT_BY_ID = "SELECT * FROM $TABLE_PRODUCT WHERE id=:id "
        const val QUERY_DELETE_PRODUCT = "DELETE FROM $TABLE_PRODUCT"
    }
}