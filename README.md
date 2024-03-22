What is LifxControl?
====================

LifxControl is a pet project of mine that I have been developing on and off for a few years now.  
It aims to be an easy-to-use text based prompter that allows users to manage LIFX lights on their local network via TCP/UDP messages.  
The java version this program uses is Java 21 (not LTS, I know...), and you can install that [here](https://jdk.java.net/java-se-ri/21).


Installation
============

Until I figure out how to package the programs as .jar files, you will just have to download the source code and the jdk.


Usage
=====

Once you install and extract the zip file, just head to "src/main/java/control/lifx" and run "java Main.java".  
Then, you can run the "help" command and a document will be printed.  
If you'd like, you can read the source of the help document.  This file is called Commands.txt, and it is located in the top folder of the project, just above README.md


What is in the future for LifxControl?
======================================

I aim to add a GUI version of this program sometime in the future.  I have the basic skeleton created already in the [GUI-Development](https://github.com/Turbojax07/LifxControl/tree/GUI-Development) branch.  However, it only allows you to interact with 3 lights on your network, and doesn't like working very well in general.