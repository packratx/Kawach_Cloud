@echo off
setlocal
set APP_HOME=%~dp0
set CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

if not exist "%CLASSPATH%" (
  echo Gradle wrapper JAR is missing. Run: gradle wrapper
  exit /b 1
)

if defined JAVA_HOME (
  "%JAVA_HOME%\bin\java" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
) else (
  java -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
)
