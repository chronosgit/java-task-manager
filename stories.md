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
- `clear` (clears the screen)

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
- `tasks view -a` (shows all tasks)
- `tasks view -e` (shows expired tasks)
- `tasks view -c` (shows compelted tasks)
- `tasks view -u` (shows uncompleted tasks)
- `tasks view <task_id>` (shows the task with ID)
- `tasks create <title> <body> [end_date]` (creates a new task with a title (1–50 chars), body (1–128 chars), ISO start date, and optional ISO end date)
- `tasks update <task_id>` (start updating a task with ID)
- `tasks complete -a` (completes all tasks)
- `tasks complete <task_id> [<task_id> ...]` (completes task/tasks with ID/IDs)
- `tasks uncomplete -a` (uncompletes all tasks)
- `tasks uncomplete <task_id> [<task_id> ...]` (uncompletes task/tasks with ID/IDs)
- `tasks delete <task_id> [<task_id> ...]` (deletes task/tasks with ID/IDs)