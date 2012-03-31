

/**
 * This script should prepare a workstation, setting path and stuff.
 * @author sinarf 
 */

class WorkstationSetup {
	final  START_TAG = "### Start Groolbox - Do not edit manually, update the script instead ###"
	final  END_TAG = "### End Groolbox - Do not edit manually, update the script instead ###"
	final  EOL = '\n'

	/** 
	 * Extract the content of the file 
	 * @param file
	 * @return the content of the file without the groolbox edited part. 
	 */
	def captureFileContentWithoutGrooboxEditedPart(File file){
		String newContent = ""
		boolean groolboxContent = false

		file.eachLine { line ->
			if(START_TAG == line) groolboxContent = true
			else if (END_TAG == line) groolboxContent = false
			else if (!groolboxContent) newContent += line + EOL
		}

		if (groolboxContent) // start tag with no end tag
			throw new RuntimeException( "Start tag with no close tag - operation aborted. Please clean the file $file MANUALLY.")
		else return newContent
	}

	def wrapContentInTags (String contentToAppend){
		START_TAG  + contentToAppend +	END_TAG + EOL
	}
	
	static main(args){
		println "Setting up the workstation. "

		File profile = new File("${System.getenv ('HOME')}/.profile")
		println profile.text 
		println "--------------------------------------------------------"
		def contentToAppend = """
source /home/sinarf/Dropbox/config/linux/profile/devel.sh
source /home/sinarf/Dropbox/config/linux/profile/alias.sh
"""
		new WorkstationSetup().with {
			def cleanFileContent = captureFileContentWithoutGrooboxEditedPart(profile)
			def editedContent = wrapContentInTags (contentToAppend) 
			profile.write(cleanFileContent + EOL + editedContent)
		}
		
	}
}
