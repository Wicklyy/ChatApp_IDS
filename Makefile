all : Info_itf Info_impl Hello Hellomlp HelloServer HelloClient 

Hello:
	javac -d classes -classpath .:classes src/Hello.java 
	cd classes; \
	jar cvf ../lib/Hello.jar Hello.class

Info_itf:
	javac -d classes -classpath .:classes src/Info_itf.java 
	cd classes; \
	jar cvf ../lib/Info_itf.jar Info_itf.class

Hellomlp:
	javac -d classes -classpath .:classes src/HelloImpl.java 
	cd classes; \
	jar cvf ../lib/HelloImpl.jar HelloImpl.class

Info_impl:
	javac -d classes -classpath .:classes src/Info_impl.java 
	cd classes; \
	jar cvf ../lib/Info_impl.jar Info_impl.class

HelloServer:
	javac -d classes -cp .:classes:lib/Hello.jar:lib/HelloImpl.jar src/HelloServer.java

HelloClient: 
	javac -d classes -cp .:classes:lib/Hello.jar src/HelloClient.java


clean:
	rm classes/* lib/*