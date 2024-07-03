FROM quay.io/quarkus/ubi-quarkus-native-image:22.3.0-java17 AS build
WORKDIR /work
COPY . /work
RUN ./mvnw package -Pnative -Dquarkus.native.container-build=true

FROM quay.io/quarkus/quarkus-micro-image:2.7.3.Final
WORKDIR /work
COPY --from=build /work/target/*-runner /work/application

EXPOSE 8085
ENTRYPOINT ["/work/application", "-Dquarkus.http.host=0.0.0.0"]