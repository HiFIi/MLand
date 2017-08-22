#!/bin/bash
for d in ./*/ ; do (cd "$d" && find -name '*.png' -print0 | xargs -0 optipng -nc -nb -o7); done
