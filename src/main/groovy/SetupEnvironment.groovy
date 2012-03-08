if ("root" == "whoami".execute().text.trim()){
	println "this script shouldn't be run as root"
	System.exit(1)
}


println  "correct the dumb ass idea to move button on the left side of the window"
Utils.executeInShell "gconftool-2 --set /apps/metacity/general/button_layout --type string menu:minimize,maximize,close"