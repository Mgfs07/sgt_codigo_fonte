FROM adoptopenjdk:11-jre-hotspot

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS="" \
    TZ="America/Sao_Paulo"

RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install -y ttf-dejavu xfonts-100dpi && \
    apt-get install tzdata && \
    cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN useradd  -s /bin/sh jhipster
WORKDIR /home/jhipster

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8081

ADD *.jar app.jar

