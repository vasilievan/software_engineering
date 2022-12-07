# OBJ-viewer

Main:
[![Tests](https://github.com/vasilievan/software_engineering/actions/workflows/gradle-tests.yml/badge.svg?branch=main)](https://github.com/vasilievan/software_engineering/actions/workflows/gradle-tests.yml)
Develop:
[![Tests](https://github.com/vasilievan/software_engineering/actions/workflows/gradle-tests.yml/badge.svg?branch=develop)](https://github.com/vasilievan/software_engineering/actions/workflows/gradle-tests.yml)

## Tool to view .obj 3D objects

### To start the server

Print in console ./gradlew run (Linux) or gradlew run (Windows), last jre should be intalled and JAVA_HOME set

# OR

Using docker: docker build -t enthusiasticprogrammer/obj_viewer . and start it with port mapping: docker run -p 80:80 enthusiasticprogrammer/obj_viewer.

### When the server is up
Open browser, print localhost. Then Just select an .obj file you want to view

![Alt text](pics/1.png?raw=true "Browse file")

## When the model is loaded:

- It can be rotated from left to right using Left and Right arrows;
- From bottom to top using Top and Bottom arrows;
- Using mouse wheel you can zoom in/out.

![Alt text](pics/2.png?raw=true "Rotate left/right")
![Alt text](pics/3.png?raw=true "Rotate top/bottom")
![Alt text](pics/4.png?raw=true "Zoom in/out")