#!/bin/sh

./setupMyBox.sh &&\
sudo apt-get install libbsapi policykit-1-fingerprint-gui fingerprint-gui -yy
