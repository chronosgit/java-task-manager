# Stories

## Startup

- Render logo
- Render app name
- Render current username (if exists)
- Render brief description
- Render author with copyright

## Commands

### Main

- `help` (shows some useful commands)
- `exit` (exits the program)

### Settings

- `settings help` (shows customization commands)
- `settings show color` (shows current color)
- `settings show colorlist` (shows allowed colors)
- `settings show username` (shows current user)
- `settings set color <color>` (sets color to allowed color)
- `settings set username <value>` (set username to new username)

### Music management

- `music help` (lists all music commands)  
- `music show current` (shows the current track’s name and elapsed time)  
- `music show tracks` (lists all available tracks)  
- `music start -s` (starts playing from the the beginning) 
- `music start <track_id>` (starts playing from the specified track) 
- `music loop` (enables looping for the current track)  
- `music unloop` (disables looping for the current track)  
- `music restart` (restarts the current track from the beginning)  
- `music pause` (pauses the current track) 
- `music continue` (resumes playback from pause) 
- `music stop` (stops playback and resets to the start)  

### Task management

- `tasks help` (show task commands)
- `tasks view` (shows all tasks)
- `tasks view -e` (shows expired tasks)
- `tasks view -c` (shows crossed tasks)
- `tasks view -u` (shows uncrossed tasks)
- `tasks create` (starts a new task creation)
- `tasks update <task_id>` (start updating a task with ID)
- `tasks cross <task_id>` (crosses task/tasks with ID/IDs)
- `tasks uncross <task_id>` (uncrosses task/tasks with ID/IDs)
- `tasks delete <task_id>` (deletes task/tasks with ID/IDs)