all : userItf ChatAppitf userImpl ChatAppImpl ClientHandler  ServerChatApp Client 


userItf:
	javac -d classes -classpath .:classes src/Interface/userItf.java
	cd classes; \
	jar cvf ../lib/userItf.jar Interface/userItf.class

ChatAppitf:
	javac -d classes -classpath .:classes src/Interface/ChatAppItf.java
	cd classes; \
	jar cvf ../lib/ChatAppItf.jar Interface/ChatAppItf.class

ChatAppImpl:
	javac -d classes -classpath .:classes src/Server/ChatAppImpl.java
	cd classes; \
	jar cvf ../lib/ChatAppImpl.jar Server/ChatAppImpl.class

ClientHandler:
	javac -d classes -classpath .:classes src/Client/ClientHandler.java
	cd classes; \
	jar cvf ../lib/ClientHandler.jar Client/ClientHandler.class

userImpl:
	javac -d classes -classpath .:classes src/Client/userImpl.java
	cd classes; \
	jar cvf ../lib/userImpl.jar Client/userImpl.class


Client:
	javac -d classes -cp .:classes:lib/Client.jar:lib/ src/Client/Client.java

ServerChatApp:
	javac -d classes -cp .:classes:lib/Server/ServerChatApp.jar:lib/ src/Server/ServerChatApp.java


clean:
	rm classes/*/* lib/*