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

package com.thoughtlogix.examples

import com.thoughtlogix.examples.controllers.AuthController
import com.thoughtlogix.advancedstarter.Server
import com.thoughtlogix.advancedstarter.settings.Settings
import com.thoughtlogix.examples.controllers.AccountController
import com.thoughtlogix.examples.controllers.ErrorController
import com.thoughtlogix.examples.controllers.MainController
import com.thoughtlogix.examples.controllers.TodoController
import com.thoughtlogix.advancedstarter.db.JPA
import com.thoughtlogix.advancedstarter.db.SeedData
import com.thoughtlogix.examples.settings.ExampleSettings
import spark.Spark
import spark.debug.DebugScreen

class ExampleServer(args: Array<String>) : Server(args) {

    override var settings = ExampleSettings()

    init {
        initServer(args)
        setupDatabase()
        displayStartupMessage(settings.systemSettings.generalSettings.title);
        displaySystemProperties()
        initControllers()
    }

    private fun initServer(args: Array<String>) {
        settings.load()
        parseArgs(args);
        super.initServer();
    }

    private fun setupDatabase() {
        jpa = JPA(settings.systemSettings.databaseSettings)
        //Migrations.runAll(settings.getDatabaseSettings());
        val seedData = SeedData(jpa as JPA)
        seedData.loadData(true)
    }

    private fun initControllers() {
        MainController(jpa!!)
        AuthController(jpa!!)
        TodoController(jpa!!)
        AccountController(jpa!!)
        ErrorController(jpa!!)
    }

}
