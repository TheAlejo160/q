# Minecraft Crouch Plugin Development Plan

## Project Overview
A Minecraft Paper plugin for version 1.19.4 that allows players to enter a "creeping" state by pressing shift multiple times and exit by pressing spacebar once.

## Core Features

### 1. Shift Key Detection
- Track consecutive shift key presses per player
- Configurable threshold (default: 5 presses)
- Reset counter after timeout or successful activation
- Handle rapid key press detection

### 2. Spacebar Detection
- Single spacebar press to exit crouch state
- Only active when player is in "creeping" mode

### 3. Player State Management
- Track which players are in "creeping" state
- Store player-specific data (press counts, timestamps)
- Clean up data when players disconnect

### 4. Configuration System
- config.yml with configurable values:
  - Shift press threshold
  - Timeout between presses
  - Message customization
  - Enable/disable plugin features

### 5. Messaging System
- Spanish message: "&7&l[&c&lTeroCraft&7&l] &fTe estas agachando"
- Color code support
- Optional sound effects

## Technical Architecture

### 1. Main Plugin Class (`TeroCraftPlugin.java`)
- Plugin initialization
- Configuration loading
- Command registration
- Event listener registration

### 2. Event Handlers
- `PlayerToggleSneakEvent` for shift detection
- `PlayerJumpEvent` for spacebar detection
- `PlayerQuitEvent` for cleanup

### 3. Player Data Management (`PlayerDataManager.java`)
- HashMap to store player states
- Methods for tracking press counts
- Cleanup utilities

### 4. Configuration Manager (`ConfigManager.java`)
- Load and validate config.yml
- Provide default values
- Reload configuration command

### 5. Message Handler (`MessageHandler.java`)
- Format messages with color codes
- Send messages to players
- Optional sound effects

## File Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── terocraft/
│   │           └── crouch/
│   │               ├── TeroCraftPlugin.java
│   │               ├── listeners/
│   │               │   └── PlayerEventListener.java
│   │               ├── managers/
│   │               │   ├── PlayerDataManager.java
│   │               │   ├── ConfigManager.java
│   │               │   └── MessageHandler.java
│   │               └── models/
│   │                   └── PlayerCrouchData.java
│   └── resources/
│       ├── plugin.yml
│       └── config.yml
├── pom.xml
└── README.md
```

## Development Phases

### Phase 1: Project Setup
- [ ] Initialize Maven project structure
- [ ] Configure pom.xml with Paper API dependencies
- [ ] Create basic plugin.yml
- [ ] Set up main plugin class

### Phase 2: Core Event System
- [ ] Implement shift key detection
- [ ] Create player data tracking
- [ ] Basic message sending functionality
- [ ] Test shift press counting

### Phase 3: Spacebar Integration
- [ ] Implement jump event detection
- [ ] Connect spacebar to crouch state exit
- [ ] Test complete crouch/stand cycle

### Phase 4: Configuration System
- [ ] Create config.yml with default values
- [ ] Implement configuration loading
- [ ] Add reload command
- [ ] Validate configuration values

### Phase 5: Polish & Testing
- [ ] Add comprehensive error handling
- [ ] Optimize performance
- [ ] Test with multiple players
- [ ] Documentation and comments

### Phase 6: Advanced Features (Optional)
- [ ] Sound effects on crouch/stand
- [ ] Permission system
- [ ] Admin commands
- [ ] Metrics/statistics

## Configuration Schema (config.yml)
```yaml
# TeroCraft Crouch Plugin Configuration
plugin:
  enabled: true
  
crouch:
  # Number of shift presses required to crouch
  shift-presses: 5
  # Maximum time between presses (milliseconds)
  press-timeout: 2000
  # Enable debug messages
  debug: false

messages:
  # Message when player starts crouching
  crouch-message: "&7&l[&c&lTeroCraft&7&l] &fTe estas agachando"
  # Message when player stands up (optional)
  stand-message: "&7&l[&c&lTeroCraft&7&l] &fTe has levantado"
  
sounds:
  # Play sound on crouch
  crouch-sound: "BLOCK_NOTE_BLOCK_BASS"
  # Play sound on stand
  stand-sound: "BLOCK_NOTE_BLOCK_BELL"
  enabled: true
```

## API Dependencies
- Paper API 1.19.4
- Maven for build management
- Java 17+ compatibility

## Testing Strategy
- Unit tests for configuration loading
- Integration tests for event handling
- Manual testing with multiple players
- Performance testing with rapid key presses

## Deployment
- Build JAR with Maven
- Test on local Paper server
- Validate configuration loading
- Documentation for server administrators

## Future Enhancements
- Database storage for persistent data
- Integration with other plugins
- Web interface for configuration
- Player statistics tracking
- Custom animations or effects