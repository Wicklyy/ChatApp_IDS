all : userItf Chanel ChatAppitf ClientChatApp Chat ChatList userImpl ChatAppImpl  ServerChatApp Main

Chanel:
	javac -d classes -classpath .:classes src/Server/Chanel.java
	cd classes; \
	jar cvf ../lib/Chanel.jar Server/Chanel.class

ClientChatApp:
	javac -d classes -classpath .:classes src/Client/ClientChatApp.java
	cd classes; \
	jar cvf ../lib/ClientChatApp.jar Client/ClientChatApp.class

Chat:
	javac -d classes -classpath .:classes src/Client/UI/Chat.java
	cd classes; \
	jar cvf ../lib/Chat.jar Client/UI/Chat.class

ChatList:
	javac -d classes -classpath .:classes src/Client/UI/ChatList.java
	cd classes; \
	jar cvf ../lib/ChatList.jar Client/UI/ChatList.class

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

userImpl:
	javac -d classes -classpath .:classes src/Client/userImpl.java
	cd classes; \
	jar cvf ../lib/userImpl.jar Client/userImpl.class

Main:
	javac -d classes -cp .:classes:lib/Main.jar:lib/ src/Client/Main.java

ServerChatApp:
	javac -d classes -cp .:classes:lib/Server/ServerChatApp.jar:lib/ src/Server/ServerChatApp.java


clean:
	rm classes/* classes/*/* classes/*/*/* lib/*