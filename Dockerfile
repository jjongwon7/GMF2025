FROM openjdk:17-jdk-alpine

# 빌드 시 jar 파일 경로를 ARG로 받음
ARG JAR_FILE=build/libs/2025-0.0.1-SNAPSHOT.jar

# 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# ENTRYPOINT에서 복사한 파일 이름 사용
ENTRYPOINT ["java", "-jar", "/app.jar"]