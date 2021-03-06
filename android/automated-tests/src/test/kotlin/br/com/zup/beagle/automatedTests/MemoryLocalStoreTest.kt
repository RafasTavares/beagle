/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.beagle.automatedTests

import br.com.zup.beagle.automatedTests.config.MemoryLocalStore
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MemoryLocalStoreTest {

    @MockK
    private lateinit var cache: MutableMap<String, String>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cache = MemoryLocalStore.getPrivateField("cache")
        cache.clear()
    }

    @Test
    fun save_should_call_lruCache_put() {
        // Given
        val key = "key"
        val value = "value"

        // When
        MemoryLocalStore.save(key, value)

        // Then
        assertEquals(1, cache.size)
        assertEquals(key, cache.keys.first())
        assertEquals(value, cache.values.first())
    }

    @Test
    fun restore_should_call_lruCache() {
        // Given
        val key = "key"
        val value = "value"

        // When
        MemoryLocalStore.save(key, value)
        val restoredValue = MemoryLocalStore.restore(key)

        // Then
        assertEquals(restoredValue, value)
    }
}