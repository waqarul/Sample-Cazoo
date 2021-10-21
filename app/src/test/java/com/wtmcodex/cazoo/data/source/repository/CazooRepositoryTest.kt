package com.wtmcodex.cazoo.data.source.repository


import com.wtmcodex.cazoo.MainCoroutineRule
import com.wtmcodex.cazoo.TestUtils.fakedProductResponse
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.core.data.source.local.CazooProductLocalDataSource
import com.wtmcodex.cazoo.core.data.source.remote.CazooProductRemoteDataSource
import com.wtmcodex.cazoo.core.data.source.repository.CazooProductRepositoryImpl
import com.wtmcodex.cazoo.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CazooRepositoryTest {
    @get:Rule
    var rule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: CazooProductRemoteDataSource

    @Mock
    private lateinit var localDataSource: CazooProductLocalDataSource

    private lateinit var systemUnderTest: CazooProductRepositoryImpl

    @Before
    fun setUp() {
        systemUnderTest =
            CazooProductRepositoryImpl(localDataSource, remoteDataSource, Dispatchers.Main)
    }

    @Test
    fun `assert that getProducts fetches successfully from the remote source`() =
        mainCoroutineRule.runBlockingTest {
            `when`(remoteDataSource.getProducts()).thenReturn(Result.Success(fakedProductResponse))

            val response = systemUnderTest.getProducts()

            verify(remoteDataSource, times(1)).getProducts()

            val fakedProductItem = fakedProductResponse.products.get(0)
            when (response) {
                is Result.Success -> {
                    val response = response.data
                    MatcherAssert.assertThat(
                        response as ProductResponse,
                        `is`(CoreMatchers.notNullValue())
                    )
                    val product = response.products[0]
                    MatcherAssert.assertThat(product.id, `is`(fakedProductItem.id))
                    MatcherAssert.assertThat(product.name, `is`(fakedProductItem.name))
                    MatcherAssert.assertThat(
                        product.description,
                        `is`(fakedProductItem.description)
                    )
                    MatcherAssert.assertThat(product.price, `is`(fakedProductItem.price))

                }
            }
        }
}