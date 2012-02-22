#!/bin/sh
# @author Michel Blavin 
# 
# This script sets up my xubuntu box  after a reinstallation. 
#

# Make sure only root can run our script
if [ "$(id -u)" != "0" ]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

echo "Git integration in Nautilus" &&\
add-apt-repository -yy ppa:rabbitvcs/ppa &&\
echo "installing groovy"&&\
apt-get install -yy groovy &&\
groovy installPackages.groovy

