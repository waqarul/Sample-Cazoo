package com.wtmcodex.cazoo

import com.wtmcodex.cazoo.core.data.enum.TypeEnum
import com.wtmcodex.cazoo.core.data.model.backend.Header
import com.wtmcodex.cazoo.core.data.model.backend.Price
import com.wtmcodex.cazoo.core.data.model.backend.Product
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse


object TestUtils {
    val fakedProductResponse = ProductResponse(
        header = Header("title", "subTitle"),
        filters = listOf("alle", "available", "favorite"),
        products = listOf(
            Product(
                name = "Test Product",
                type = TypeEnum.Circle,
                id = 0,
                color = "color",
                imageURL = "url",
                colorCode = "colorCode",
                available = false,
                releaseDate = 12345678,
                description = "description",
                longDescription = "longDescription",
                rating = 2.5,
                price = Price(2.5, "EUR")
            )
        )
    )
}
