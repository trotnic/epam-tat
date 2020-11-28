#!/bin/bash
set -ex
wget https://chromedriver.storage.googleapis.com/87.0.4280.20/chromedriver_mac64.zip
unzip chromedriver_mac64.zip
sudo mv chromedriver /usr/local/bin
