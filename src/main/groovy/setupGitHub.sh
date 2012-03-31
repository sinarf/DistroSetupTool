# Make sure the scritp is not run as root
if [ "$(id -u)" = "0" ]; then
   echo "This script shouldn't be run as root" 1>&2
   exit 1
fi

cd ~/.ssh/
ssh-keygen -t rsa -C "michel@blavin.fr"
echo "Goto https://github.com/account/ssh and paste the following "
echo "********************************************************************************"
cat ~/.ssh/id_rsa.pub
echo "********************************************************************************"
google-chrome https://github.com/account/ssh
