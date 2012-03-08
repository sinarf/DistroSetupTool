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

echo "installing groovy"&&\
apt-get install -yy groovy &&\
groovy $SCRIPT_DIR/InstallPPA.groovy &&\
groovy $SCRIPT_DIR/InstallPackages.groovy

echo correct the dumb ass idea to move button on the left side of the window 
gconftool-2 --set /apps/metacity/general/button_layout --type string menu:minimize,maximize,close
