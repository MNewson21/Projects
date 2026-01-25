# Changelog
### 30/12/2025 - Initial commit of changelog
**Added:** 
- Added readme.md to SeaGame project
- Added changelog.md to SeaGame project

**Removed:**
- Removed Java_Sea_Game readme.md since it was replaced by the readme.md in SeaGame instead

**Current Game Status:** Game launches, and you can move the turtle sprite around.


### 31/12/2025 - Upgraded Testing to use GitHub actions and added collision
**Added:**
- TurtleCollision.java so the turtle can collide with the boundaries and no longer go off the screen
- seagame-ci.yml for automated testing of AppTest.java upon push

**Current Game Status:** Game launches and turtle can not go off the screen. Current tests also get run on runner

### 02/12/2025 - Added new tests and fixed headless testing issue using Xvfb
Commit ID - 4b70305e0e01e41c2d53aeb6cda3d636bb4f4761

**Added:**
- Xvfb to seagame-ci.yml which mimics a virtual screen on the headless runner which allows it to run the JavaFX tests


**Removed:**
- Monocle from pom.xml since it had version issues and could not get headless to work on Java 24. Hence, moved to Xvfb

**Current Game Status:** Game launches and works as on the 31st, and now automated regression tests run on the pipeline correctly

### 03/12/2025 - Added LoFi Seaweed to the game
Commit ID - f1ff213c879655f18a33f5f02039ba314f2ab1d2

**Added:**
- GateManager.java to spawn the individual seaweed gates
- Gate.java for the turtle to swim through

**Current Game Status:** Game works as on the 2nd, and now the turtle can swim through the seaweed gates and they will respawn at a different height.

### 05/01/2026 - Added Score
**Added:**
- Score to fxml file
- GameController reference to game so it can interact with the UI

**Current Game Status:** Game works as on the 3rd but now has score that increments when the Turtle passes through the gate

### 25/01/2026 - Added Background Music and corrected positions 
**Added:**
- SoundManager.java - When created with a passed string argument, it will check the Sounds directory in resources and play that file
- Maven dependency for media

**Changed:** 
- Positions of objects are now default to a 1920x1080 screen compared to my laptop screen
- Some positional adjustments of FXML items