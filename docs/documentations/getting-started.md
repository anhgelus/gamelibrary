---
layout: page
title: "Getting Started"
permalink: /documentations/getting-started
---

The creation of a game was simplified with GameLibrary, but you should learn how it works to create a good game.

Of course, this is the documentations for the developer, not for the player.

## Installation
Install the plugin with Gradle:

```gradle
repositories {
    maven {
        url 'https://jitpack.io'
    }
}
```

```gradle
dependencies {
    implementation 'com.github.anhgelus:gamelibrary:VERSION'
}
```

## Create a game
To create a game, you just need to create a new instance of Game.

The constructor need the following parameters:
- `main`: The plugin instance
- `name`: The name of the game

```java
final Plugin main = this; // If you are in the main file, of course
final String name = "Custom Game"; // The name of the game
final Game game = new Game(main, name);
```

### Conditions

With this instance, you can modify every condition.

You must create at least 3 classes who implement condtions:
- `GeneralConditions` (General conditions, not specific)
- `StartConditions` (Starting conditions)
- `WinConditions` (Wining conditions)

```java
class GConditions implements GeneralConditions {
    @Override
    public void onPause() {
        // Pause logic
    }
    @Override
    public void onResume() {
        // Resume logic
    }
}
```

```java
class SConditions implements StartConditions {
    @Override
    public void onStart() {
        // Startup logic
    }
}
```

```java
class WConditions implements WinConditions {
    @Override
    public void onWin(Game game) {
        // Win logic
    }
}
```

After creating these classes, you can register it in the game engine.

```java
final GameEngine engine = game.getEngine();
engine.setWinConditions(new WConditions());
engine.setStartConditions(new SConditions());
engine.setGeneralConditions(new GConditions());
```

### Register commands
Before starting the game, you must register the game commands.

```java
final GameCommandManager manager = game.getCommandManager();

getCommand("game").setExecutor(manager.registerGameCommand());
```

When the tab completer is created, you will be register the tab completions. 

## Conclusion
Now you can start the game!

Of course, it's a basic game without any teams, any persistent data, any configuration and any custom commands.
