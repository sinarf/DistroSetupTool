
/**
* Execute anything that can run in a shell.
*
* @param cmd command to run
* @exception RuntimeException throw a runtime exception if cmd sending anything to the error stream.
*/
static def executeInShell(cmd) {
   println "Excecuting command: '$cmd'"
   def proc = "$cmd".execute()
   def input = proc.inputStream
   def c = input.read()
   while (c > -1) {
	   char car = c
	   print  car
	   c = input.read()
   }
   proc.waitFor()
   // checks for errors.
   def errors = proc.err.text
   !errors ?: System.err.println("ERRORS: '${errors}'")

}
