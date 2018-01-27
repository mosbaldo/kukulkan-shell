<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>${project.packaging}</groupId>
	<artifactId>${project.id}</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>${project.id?cap_first}</name>
	<packaging>jar</packaging>

	<properties>
		<antlr4.version>4.5.2</antlr4.version>
		<antlr4.plugin.version>4.5.2</antlr4.plugin.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<build>
		<plugins>
			<plugin>
				<groupId>org.antlr</groupId>
				<artifactId>antlr4-maven-plugin</artifactId>
				<version>${r"${antlr4.plugin.version}"}</version>
				<configuration>
					<arguments>
						<argument>-visitor</argument>
					</arguments>
				</configuration>
				<executions>
					<execution>
						<id>antlr</id>
						<goals>
							<goal>antlr4</goal>
						</goals>
					</execution>
				</executions>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
		</plugins>
	</build>

	<dependencies>
		<dependency>
			<groupId>org.antlr</groupId>
			<artifactId>antlr4-runtime</artifactId>
			<version>${r"${antlr4.version}"}</version>
		</dependency>

		<dependency>
			<groupId>org.antlr</groupId>
			<artifactId>antlr4-maven-plugin</artifactId>
			<version>${r"${antlr4.plugin.version}"}</version>
		</dependency>
	</dependencies>
	
</project>
