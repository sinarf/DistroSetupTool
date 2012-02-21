#!/usr/bin/groovy

/**
 * Install package.
 */

def applicationsFilePath = "${new File(getClass().protectionDomain.codeSource.location.path).parent}/applications.txt"

def apps = ""
executeInShellAndWait "apt-get update"
executeInShellAndWait "apt-get -yy upgrade"

println "Installing my applications from "

def applicationsFile = new File(applicationsFilePath)
if (applicationsFile.exists())
	applicationsFile.eachLine { if (!it.startsWith("#")) apps += "$it "}
else {
	System.err.println "Please provide a application file: $applicationsFilePath"
} 
	
executeInShellAndWait "apt-get install -yy $apps"
// executeInShellAndWait "dpkg -i /home/sinarf/Dropbox/softs/linux/*.deb"

sleep 5000

/**
 * 
 * 
 * 
 * @param cmd command to run
 * @exception throw a runtime exeption if cmd sending anything to the 
 */
def executeInShellAndWait(cmd) {
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
	String errMsg = proc.err.text
	
	if (errMsg ) throw new RuntimeException (errMsg)
	
}
