export CLASSPATH=$CLASSPATH:./classes 
rmiregistry 6090 &
java -Djava.rmi.server.hostname=localhost  -cp classes Server/ServerChatApp 6090