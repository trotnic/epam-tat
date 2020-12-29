# epam-tat
---
## Environment config
### Ubuntu 20.04.1 LTS
### JDK - openjdk version "1.8.0_265"
#### Installation
1. Run the commands below in the terminal
  - `sudo apt-get update`
  - `sudo apt-get install openjdk-8-jdk`
2. Add `export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64` inside the `~/.bashrc` file and run `source ~/.bashrc`
#### Apache Maven 3.5.2
1. Run the commands below in the terminal
  - `cd /opt`
  - `wget -O- https://archive.apache.org/dist/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz | sudo tar -xzv`
2. Add `export PATH="/opt/apache-maven-3.5.2/bin:$PATH"` inside the `~/.bashrc` file and run `source ~/.bashrc`
#### Jenkins 2.249.2
1. Run the commands below in the terminal
  - `wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -`
  - `sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'`
  - `sudo apt update`
  - `sudo apt install jenkins`
--- 
### Default paths
1. Jenkins config: `/etc/default/jenkins`
---
