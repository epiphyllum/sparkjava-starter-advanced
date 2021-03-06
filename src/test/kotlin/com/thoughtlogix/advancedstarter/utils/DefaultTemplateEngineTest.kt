/*
 * MIT License
 *
 * Copyright (c) 2016 Thought Logix
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.thoughtlogix.advancedstarter.utils

import com.thoughtlogix.advancedstarter.server.TemplateEngine
import org.testng.Assert
import org.testng.annotations.Test
import java.util.*

class DefaultTemplateEngineTest {
    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun testRenderWithMissingTemplate() {
        val renderer = TemplateEngine()
        val model = HashMap<String, String>()
        model.put("foo", "bar")
        renderer.render(model, "fake.peb")
    }

    @Test
    fun testRenderWithValidTemplate() {
        val renderer = TemplateEngine()
        val model = HashMap<String, String>()
        model.put("foo", "bar")
        val result = renderer.render(model, "/simple.peb")
        Assert.assertEquals(result, "It works")
    }
}