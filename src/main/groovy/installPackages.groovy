#!groovy
def scriptDir = new File(getClass().protectionDomain.codeSource.location.path).parent

def apps = ""
new File("$scriptDir/applications.txt").eachLine { if (!it.startsWith("#")) apps += "$it "}
def cmd = "sudo apt-get install -yy $apps"
executeInShellAndWait(cmd)
executeInShellAndWait("sudo dpkg -i /home/sinarf/Dropbox/softs/linux/*.deb")

sleep 5000


def executeInShellAndWait(cmd) {
	println "Excecuting command on a external terminal: '$cmd'"
	def proc = "xterm -e $cmd".execute()
	proc.waitFor()
}
