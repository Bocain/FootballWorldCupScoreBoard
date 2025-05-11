# Football World Cup Score Board Library

A simple Java library for managing a Football World Cup score board — matches can be started, updated, finished, and listed as a summary of games by total score.

---

## Requirements

- Java **8** or higher
- Maven **3.6+**

## Installation (Maven)

### 1. Create a `lib` folder in the root of your project

```
project-root/
├── lib/
├── src/
└── target/
```

### 2. Add the dependency to your `pom.xml`

```xml
<dependency>
  <groupId>io.github.Bocain</groupId>
  <artifactId>FootballWorldCupScoreBoard</artifactId>
  <version>2.0.1</version>
</dependency>
```

### 3. Install the JAR locally

```
mvn install:install-file \
  -Dfile=lib/FootballWorldCupScoreBoard-2.0.1.jar \
  -DgroupId=io.github.Bocain \
  -DartifactId=FootballWorldCupScoreBoard \
  -Dversion=2.0.1 \
  -Dpackaging=jar
```

### 4. Build your project

```bash
mvn clean install
```

---

## Usage Example

Here's a basic example of how to use the FootballWorldCupScoreBoard library:

```java
import io.github.bocain.ScoreBoard;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreboard = new ScoreBoard();

        scoreboard.startGame("Poland", "USA");

        scoreboard.getSummary().forEach(System.out::println); 
        // returns "Poland 0 - 0 USA"
    }
}
```

## Owners & Contributors

- **Bocain** – [allegrodolar@vp.pl]
