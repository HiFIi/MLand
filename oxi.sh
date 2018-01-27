#!/bin/bash
# 08/22/17: Added threading
other () {
for f in "*.png.webp"; do mv "$f" "${f#.png}"; done
}
for d in ./*/ ; do other; done


