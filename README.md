# epam-tat

# Environment config
### Ubuntu 20.04.1 LTS
### JDK - openjdk version "1.8.0_265"
- `sudo apt-get update`
- `sudo apt-get install openjdk-8-jdk`
- `export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64`
### Apache Maven 3.5.2
- `cd /opt`
- `wget -O- https://archive.apache.org/dist/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz | sudo tar -xzv`
- `export PATH="/opt/apache-maven-3.5.2/bin:$PATH"`
### Jenkins 2.249.2
- `wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -`
- `sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'`
- `sudo apt update`
- `sudo apt install jenkins`
