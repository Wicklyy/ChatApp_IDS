all : ChatAppitf userItf ServerChatApp Client 

userItf:
	javac -d classes -classpath .:classes src/Interface/userItf.java
	cd classes; \
	jar cvf ../lib/userItf.jar Interface/userItf.class

ChatAppitf:
	javac -d classes -classpath .:classes src/Interface/ChatAppItf.java
	cd classes; \
	jar cvf ../lib/ChatAppitf.jar Interface/ChatAppitf.class

Client:
	javac -d classes -cp .:classes:lib/Client.jar:lib/ src/Client.java

ServerChatApp:
	javac -d classes -cp .:classes:lib/ServerChatApp.jar:lib/ src/ServerChatApp.java


clean:
	rm classes/* lib/*