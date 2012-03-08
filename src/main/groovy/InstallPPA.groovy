/**
 * Install new ppas from a file in PPA_FILE environment variable $Dropbox/config/DistroSetupTool.txt.
 * @author sinarf
 *
 */

def ppaFilePath = System.getenv()["PPA_FILE"] ?: "${System.getenv()['HOME']}/Dropbox/config/DistroSetupTool/ppa.txt"

println "Using file $ppaFilePath"
new File(ppaFilePath).eachLine { ppa ->
	Utils.executeInShell "add-apt-repository -yy $ppa"
}
