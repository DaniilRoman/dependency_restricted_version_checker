# Enforcer custom rule to ckeck restricted dependency's versions
Plugin is going to check project doesn't have depdendecy's versions like RELEASE, SNAPSHOT or others dynamic changing versions.  

# Usage
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-enforcer-plugin</artifactId>
                <version>3.0.0</version>
                <dependencies>
                    <dependency>
                        <groupId>com.github.DaniilRoman</groupId>
                        <artifactId>dependency_restricted_version_check</artifactId>
                        <version>1.0-SNAPSHOT</version>
                    </dependency>
                </dependencies>
                <executions>
                    <execution>
                        <id>enforce</id>
                        <configuration>
                            <rules>
                                <myCustomRule implementation="org.apache.maven.plugins.enforcer.RestrictDependencyVersionRule">
                                    <enabled>false</enabled>
                                </myCustomRule>
                            </rules>
                        </configuration>
                        <goals>
                            <goal>enforce</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

# Dependency

## Maven

```xml
<dependency>
    <groupId>io.github.DaniilRoman</groupId>
    <artifactId>dependency_restricted_version_check</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```