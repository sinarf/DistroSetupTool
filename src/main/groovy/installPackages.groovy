#!/usr/bin/env groovy

/**
 * 
 * Install packages.
 * 
 * @author Michel Blavin
 */

def applicationsFilePath = "${new File(getClass().protectionDomain.codeSource.location.path).parent}/applications.txt"

def apps = ""
def applicationsFile = new File(applicationsFilePath)
if (applicationsFile.exists())
	applicationsFile.eachLine { if (!it.startsWith("#")) apps += "$it "}
else {
	System.err.println "Please provide a application file: $applicationsFilePath"
}

[
	"Update sources..."					:	"apt-get update",
	"Update software..."				:	"apt-get -yy upgrade",
	"Install applications from $apps"	:	"apt-get install -yy $apps"
].each { message, command ->
	println message
	executeInShell command
}

sleep 5000

/**
 * Execute anything that can run in a shell.
 * 
 * @param cmd command to run
 * @exception RuntimeException throw a runtime exeption if cmd sending anything to the error stream.
 */
def executeInShell(cmd) {
	println "Excecuting command on a external terminal: '$cmd'"
	def proc = "$cmd".execute()
	proc.waitFor()
	def out = proc.inputStream
	def c = out.read()
	def error = false
	while (c > -1) {
		char car = c
		print  car
		c = out.read()
	}
	// checks for errors. 
	String errMsg = proc.err.text
	if (errMsg ) throw new RuntimeException (errMsg)
}
