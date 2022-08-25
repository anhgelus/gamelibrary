---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults
title: Home
layout: home
---
## Do you want to create some games?
Creating games in Minecraft is fun, but it's hard to do it right.

It's also very redundant, because you need to create a game engine, a team system, a game management system, and more.

This is why I decided to create this project.

## The Project

GameLibrary is a Spigot game library to create games mode easier.

It aims to be a modular library and to be used to create every game in Minecraft. Another goal is to create a lightweight
and optimized library because if the library is laggy, the plugin is laggy and the server become laggy, and the lag is 
not cool when the game is competitive.

## Installation
To install GameLibrary, you just need to put the jar file in your "plugins" folder.

You can download it from [GitHub](https://github.com/anhgelus/gamelibrary/releases/latest).

### Development
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

## Features

This project is in early development, so there are some features that are not implemented yet.

If you have any suggestions, please contact me with an issue on GitHub: [Issues](https://github.com/anhgelus/gamelibrary/issues).

- Game Management (start, stop, pause, resume)
- Game Setup (win conditions, start conditions)
- Config Management (configuration file made easier)
- Message Management (configuration file to modify message)
- Send Message to Player Helper (send message to player, colors, broadcasts, etc)
- Custom Game Subcommands (/game something)
- Basic Game Subcommands (/game start, /game stop, /game pause, /game resume)
- Vault to save some data

### Planned

- Team Management
