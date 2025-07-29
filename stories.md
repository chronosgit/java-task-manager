# Stories

## Startup

- Render logo.
- Render app name.
- Render current username (if exists).
- Render brief description.
- Render author with copyright.

## Commands

### Main

- `help` (shows some useful commands).
- `exit` (exits the program).

### Settings

- `settings help` (shows customization commands).
- `settings show color` (shows current color).
- `settings show colorlist` (shows allowed colors).
- `settings show username` (shows current user).
- `settings set color [VALUE]` (sets color to allowed color).
- `settings set username [VALUE]` (set username to new username).

### Music management

- `music help` (show music commands).
- `music show` (shows current track name).
- `music stop` (stops playing music).
- `music start` (starts playing music).
- `music continue` (continues playing music).

### Task management

- `tasks help` (show task commands).
- `tasks view` (shows all tasks).
- `tasks view -e` (shows expired tasks).
- `tasks view -c` (shows crossed tasks).
- `tasks view -u` (shows uncrossed tasks).
- `tasks create` (starts a new task creation).
- `tasks update [TASK_ID]` (start updating a task with ID).
- `tasks cross [TASK_ID]...` (crosses task/tasks with ID/IDs).
- `tasks uncross [TASK_ID]...` (uncrosses task/tasks with ID/IDs).
- `tasks delete [TASK_ID]...` (deletes task/tasks with ID/IDs).