# tgh-launcher
A repo for my new awsum Linux launcher app, because d-menu doesn't cut it and after some research I wasn't satisfied with the options currently available. 
##Intended developement
I plan to first make a small app in Java which will gather information about each program installed on the machine it's ran on and save it in a text file. The information shall be gathered from manual page of the program and optionally also from other sources, such as the desktop file of the program. It should then be structured in a way which makes the more relevant information faster to find. For instance, all names of the programs should come first, brief descriptions gathered from the first sentences of DESCRIPTION sections of the man pages should follow, etc.

##Optimalization

###Generator
Since the generator app will only be run sparely, the performance is not critical. However, at least some optimalization
should be taken into account, such as the option to only update program list as opposed to rebuilding it completely and more.
###Reader
The reader app should be highly optimized to a degree when its resource hungryness is comparable to that of d-menu. It will most likely be written in C.
