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

package com.thoughtlogix.advancedstarter.settings.system

import com.fasterxml.jackson.annotation.JsonIgnore
import com.thoughtlogix.advancedstarter.db.engines.DatabaseEngineFactory
import com.thoughtlogix.advancedstarter.db.engines.DatabaseEngineId
import org.hibernate.dialect.Dialect
import org.hibernate.dialect.H2Dialect

class DatabaseSettings {

    var databaseEngineId = DatabaseEngineId.H2
    var database = "advancedstarter"
    var host = ""
    var port = ""
    var username = "sa"
    var password = ""

    //    var  databaseEngineId:DatabaseEngineId = DatabaseEngineId.MYSQL
    //    var database = "advancedstarter"
    //    var host = "localhost"
    //    var port = "3306"
    //    var username = "tlogix"
    //    var password = ""

    //    var databaseEngineId: DatabaseEngineId = DatabaseEngineId.POSTGRES
    //    var database = "advancedstarter"
    //    var host = "localhost"
    //    var port = "5432"
    //    var username = "tlogix"
    //    var password = ""

    //    private  databaseEngineId:DatabaseEngineId = DatabaseEngineId.SQLSERVER
    //    var database = "advancedstarter"
    //    var host = "localhost"
    //    var port = "1433"
    //    var username = "tlogix"
    //    var password = ""

    @JsonIgnore
    var driver: String = ""
        get() = DatabaseEngineFactory.create(this.databaseEngineId).driver

    @JsonIgnore
    var url: String = ""
        get() = DatabaseEngineFactory.create(this.databaseEngineId).getUrl(this)

    @JsonIgnore
    var dialect: String = ""
        get() = DatabaseEngineFactory.create(this.databaseEngineId).dialect

    fun updateEngine() {
        driver = DatabaseEngineFactory.create(this.databaseEngineId).driver
        url = DatabaseEngineFactory.create(this.databaseEngineId).getUrl(this)
        dialect = DatabaseEngineFactory.create(this.databaseEngineId).dialect
    }
}
