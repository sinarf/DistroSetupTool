/**
 * 
 * Install packages.
 * 
 * @author Michel Blavin
 */

def applicationsFilePath = System.getenv()["APP_FILE"] ?: "${System.getenv()['HOME']}/Dropbox/config/DistroSetupTool/applications.txt"
def apps = ""
def applicationsFile = new File(applicationsFilePath)
if (applicationsFile.exists())
	applicationsFile.eachLine { if (!it.startsWith("#")) apps += "$it "}
else {
	System.err.println "Please provide a application file: $applicationsFilePath"
	System.exit(1)
}

println "Update sources..."
Utils.executeInShell "apt-get update"

println "Update software..."
Utils.executeInShell "apt-get upgrade"

println "Install applications..."

new File (applicationsFilePath).eachLine { app ->
	println 	"Install applications from $app"
	Utils.executeInShell "apt-get install -yy $apps"
}

println "Autoremove unused applications"
Utils.executeInShell "apt-get autoremove -yy"

