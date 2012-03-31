#!/bin/sh
# @author Michel Blavin 
# 
# This script sets up my ubuntu box  after a reinstallation. 
#

# Make sure only root can run our script
if [ "$(id -u)" != "0" ]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

SCRIPT_DIR=`dirname $0`
echo installing dropbox...
cd ~ && wget -O - "http://www.dropbox.com/download?plat=lnx.x86" | tar xzf -
echo start Dropbox....
~/.dropbox-dist/dropboxd

echo "installing groovy"&&\
apt-get install -yy groovy &&\
groovy $SCRIPT_DIR/InstallPPA.groovy &&\
apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E6A233DBE3AFBEFC
groovy $SCRIPT_DIR/InstallPackages.groovy

