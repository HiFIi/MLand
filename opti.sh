#!/bin/bash
# 08/22/17: Added threading

for d in ./*/ ; do (cd "$d" && find -name '*.png' -print0 | xargs -P8 -L1 -0 optipng -nc -nb -o7); done
