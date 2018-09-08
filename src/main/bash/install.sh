#!/usr/bin/env bash


git clone --recursive https://github.com/mponza/SalIE

bash src/main/bash/patch.sh
bash src/main/download-resources.sh