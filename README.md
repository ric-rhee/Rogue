# Introduction
---
This is the class project from ECE 39595J Fall 2020, which is a cut-down version of the
dungeon crawler game Rogue released in 1980

## How to run
---
1. `cd src`
2. `javac game/Rogue.java`
3. `java game.Rogue (insert xml file)`

There are 7 xml files
- badScroll.xml
- death.xml
- dropPack.xml
- dungeon.xml
- hallucinate.xml
- testDrawing.xml
- wear.xml

## How to play
---
Movement uses vim navigation keys (h, j, k, l, - left, down, up, right)


## Removing .class files
---
There is a shell script which removes all .class files generated, simply run
`./clean.sh`
from the src directory
