all : userItf ChatAppitf userImpl Chat ChatList ChatAppImpl ServerChatApp ClientChatApp Main


Chat:
	javac -d classes -classpath .:classes src/Client/UI/Chat.java
	cd classes; \
	jar cvf ../lib/userItf.jar Client/UI/Chat.class

ChatList:
	javac -d classes -classpath .:classes src/Client/UI/ChatList.java
	cd classes; \
	jar cvf ../lib/userItf.jar Client/UI/ChatList.class

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

ClientChatApp:
	javac -d classes -cp .:classes:lib/ClientChatApp.jar:lib/ src/Client/ClientChatApp.java

ServerChatApp:
	javac -d classes -cp .:classes:lib/Server/ServerChatApp.jar:lib/ src/Server/ServerChatApp.java


clean:
	rm classes/*/*/* lib/*