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

package com.thoughtlogix.advancedstarter

import com.beust.jcommander.JCommander
import com.thoughtlogix.advancedstarter.settings.Settings
import com.thoughtlogix.advancedstarter.db.JPA
import com.thoughtlogix.advancedstarter.server.CommandLineOptions
import com.thoughtlogix.advancedstarter.utils.Memory
import org.slf4j.LoggerFactory
import spark.Spark.*
import spark.debug.DebugScreen.enableDebugScreen
import spark.servlet.SparkApplication
import java.util.*

open class Server : SparkApplication {
    val logger = LoggerFactory.getLogger(Server::class.java)
    protected open val settings: Settings = Settings();
    protected var jpa: JPA? = null

    /**
     * Constructor to standalone deployment using embedded jetty web server.
     *
     * @param args Command line options
     */
    constructor(args: Array<String>) {
    }

    /**
     * Initialize the server via app server (not supported)
     *
     * @Todo: Test and fix why using app server didn't work.  I'm probably including wrong jars.  Grrr...
     */
    override fun init() {
        try {
            throw Exception("Running server using an application server is not supported yet")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun initServer() {
        Server.isDevMode = options.dev;

        if (Server.isDevMode) {
            enableDebugScreen();
        }

        port(options.serverPort.toInt())

        if (Server.isDevMode) {
            externalStaticFileLocation("src/main/resources" + options.serverStaticPath)
        } else {
            staticFileLocation(options.serverStaticPath)
        }
    }

    protected fun displayStartupMessage(title: String) {
        logger.info("=============================================================")
        logger.info(title)
        logger.info("Date: " + Date().toString())
        logger.info("OS: " + System.getProperty("os.name"))
        logger.info("Initial Memory: " + Memory.used + "Mb")
        logger.info("=============================================================")
    }

    protected fun displaySystemProperties() {
        val props = System.getProperties()
        val e = props.propertyNames()

        while (e.hasMoreElements()) {
            val key = e.nextElement() as String
            logger.info(String.format("%s = %s", key, props.getProperty(key)))
        }
    }

    protected fun parseArgs(args: Array<String>) {
        val options = CommandLineOptions()
        JCommander(options, *args)
        Server.options = options
    }

    companion object {
        var options: CommandLineOptions = CommandLineOptions();
        var isDevMode = options.dev;
    }
}
