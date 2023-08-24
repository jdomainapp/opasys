# echo "$0: (empty)"

java -jar /jda/configserver.jar & 

# (wait for it to completely finish...)

sleep 20

java -jar /jda/eurekaserver.jar & 

# (wait for it to completely finish...)

sleep 15

java -jar /jda/gatewayserver.jar &
