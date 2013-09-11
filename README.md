quantum
=======

team programming course project

how to participate
------------------

1. Login on github
2. Press *Fork* button in upper-right corner
3. *clone* git repository with git clone
4. *execute* following command to subscribe for changes from main (this) repository: **git remote add upstream git@github.com:saladinkzn/quantum.git**
5. now you can get changes with **git fetch upstream** or **git pull upstream**

how to build
------------

Windows:

1. Run make_artifacts.bat
2. Wait

how to deploy
-------------

1. Copy webapp/target/quantum-web-1.0-SNAPSHOT.war to JBOSS_HOME/standalone/deployments
2. Run JBOSS_HOME/bin/standalone.bat
3. Application will be available [here](http://localhost:8080/)