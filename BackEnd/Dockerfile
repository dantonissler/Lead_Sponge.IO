FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /root
COPY /target/leadsponge.jar leadsponge.jar

SHELL ["/bin/sh", "-c"]
#ENTRYPOINT ["java","-jar","leadsponge.jar"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar leadsponge.jar --spring.profiles.active=${PROFILE}
