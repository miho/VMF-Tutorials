# VMF Tutorial 16

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)

## VMF Maven Plugin

### What you will learn

In this tutorial you will learn

- how to use Maven and the VMF Maven Plugin to build your project

### What is the VMF Maven Plugin?

VMF supports two build systems

- Gradle
- Maven

A VMF Maven Plugin is available for those who use Maven in order to build and manage their projects and create the resulting artifacts (jar files, binaries or installers).

When you use the VMF Maven Plugin in your project build it will take care for the following tasks
- it supports VMF models in your source tree (during regular build) as well as VMF models in your test tree (during test execution)
- it scans for all VMF models in the relevant sub tree of your project (source or test) and executes two steps
  - it generates Java code for all VMF models found
  - it compiles the generated Java classes to binary class file format afterwards

### How to use the VMF Maven Plugin?

In order to use the VMF Maven Plugin you must add it to the build section of your Maven project.

An example might look like this. In the build section the plugin is added with two executions:

```xml
<build>
	<plugins>
		<plugin>
			<groupId>eu.mihosoft.vmf</groupId>
			<artifactId>vmf-maven-plugin</artifactId>
			<version>0.2.8.0</version>
			<configuration>
			</configuration>
			<executions>
				<execution>
					<id>vmf-sources</id>
					<goals>
						<goal>vmf</goal>
					</goals>
				</execution>
				<execution>
					<id>vmf-test-sources</id>
					<goals>
						<goal>vmf-test</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

The plugin has two goals "vmf-sources" and "vmf-test-sources" that can be used to generate and compile the Java classes for models located in source tree and for models located in test tree, respectively.

And remember to reference the required VMF jar files required at runtime of your project in the dependency section:

```xml
<dependencies>
	<dependency>
		<groupId>eu.mihosoft.vmf</groupId>
		<artifactId>vmf</artifactId>
		<version>0.2.8.0</version>
	</dependency>
	<dependency>
		<groupId>eu.mihosoft.vmf</groupId>
		<artifactId>vmf-runtime</artifactId>
		<version>0.2.8.0</version>
	</dependency>
</dependencies>
```

### How to get help for the VMF Maven Plugin

You can generate VMF Maven plugin help output by this invocation of the plugin:
```bash
mvn help:describe -DgroupId=eu.mihosoft.vmf -DartifactId=vmf-maven-plugin -Dversion=0.2.8.0 -Ddetail=true
```

Detailed description of the VMF Maven plugin can be found on the [plugin page in the VMF project](https://github.com/miho/VMF/tree/master/maven-plugin).

## Conclusion

Congrats, you have learned how to use the VMF Maven plugin in order to use Maven to build you project.

You can get an example Maven Pom from
[here](https://github.com/miho/VMF/blob/master/test-suite/pom.xml).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)
