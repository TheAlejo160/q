# TeroCraft Crouch Plugin

A Minecraft Paper plugin for version 1.19.4 that allows players to enter a "creeping" state by pressing shift multiple times and exit by pressing spacebar once.

## Features

- **Configurable Shift Detection**: Players can crouch by pressing shift a configurable number of times (default: 5)
- **Spacebar Exit**: Exit crouch state with a single spacebar press (jump)
- **Custom Messages**: Displays "&7&l[&c&lTeroCraft&7&l] &fTe estas agachando" when crouching
- **Sound Effects**: Optional sound effects for crouch/stand actions
- **Permission System**: Control who can use the plugin features
- **Configuration Reload**: Reload settings without restarting the server
- **Debug Mode**: Optional debug messages for troubleshooting

## Installation

1. Download the plugin JAR file
2. Place it in your server's `plugins` folder
3. Restart your server or use a plugin manager to load it
4. Configure the plugin by editing `plugins/TeroCraftCrouch/config.yml`

## Configuration

The plugin creates a `config.yml` file with the following options:

```yaml
plugin:
  enabled: true
  
crouch:
  shift-presses: 5          # Number of shift presses required
  press-timeout: 2000       # Max time between presses (ms)
  debug: false              # Enable debug messages

messages:
  crouch-message: "&7&l[&c&lTeroCraft&7&l] &fTe estas agachando"
  stand-message: "&7&l[&c&lTeroCraft&7&l] &fTe has levantado"
  
sounds:
  crouch-sound: "BLOCK_NOTE_BLOCK_BASS"
  stand-sound: "BLOCK_NOTE_BLOCK_BELL"
  enabled: true
```

## Commands

- `/terocrouchreload` - Reload the plugin configuration (requires `terocraft.crouch.reload` permission)

## Permissions

- `terocraft.crouch.use` - Allows using the crouch feature (default: true)
- `terocraft.crouch.reload` - Allows reloading configuration (default: op)

## How to Use

1. Press shift 5 times quickly (within 2 seconds by default)
2. You'll see the crouch message and enter "creeping" state
3. Press spacebar (jump) once to exit the crouch state
4. You'll see the stand message

## Building from Source

1. Clone this repository
2. Make sure you have Java 17+ and Maven installed
3. Run `mvn clean package`
4. The compiled JAR will be in the `target` folder

## Requirements

- Minecraft Server: Paper 1.19.4
- Java: 17 or higher
- API: Paper API 1.19.4

## Support

For issues, suggestions, or support, please contact the TeroCraft team.

## License

This plugin is developed for TeroCraft server. All rights reserved.