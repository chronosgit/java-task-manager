# Stories

## Main screen

- See main stuff:
  - Logo (ASCII).
  - Name of the app.
  - Version (maybe?).
  - Copyright decoration.
- Selecting a feature below:
  - Redirect to view-work all tasks.
  - Redirect for a new task creation.
  - Redirect for choose-deletion of a task.
  - Redirect for choose-update of a task.
  - Redirect to view-work all expired tasks.
  
## Tasks viewer (any tasks)

- See tasks
- A task can be selected for:
  - Viewing its details and further operations.
  - Update (redirect).
  - Delete (redirect to "Are you sure window?").
- One/multiple selected can be toggled straight up.
- Go back (to the main screen).

## Task viewer 

- See details:
  - Title.
  - Description.
  - When started.
  - Deadline (optional).
- Update (redirect).
- Delete (redirect ...).
- Go back (to all or expired tasks).

## Task update

- Edit details in form of "filled inputs".
- Save ("Saved!" pop up, until editing again).
- Discard (go back to previous state).
- Go back (to all or expired tasks, forget everything unsaved).

## Task deletion

- Prompt to confirm deletion "of ...".
- The deletion goes back automatically.
- Go back (to all or expired tasks).

## Expired tasks

- Same stuff, honestly, but with different color or something?
- Maybe to just show end date additionaly?
- Go back (to the main screen).

## File I/O

- Task schema:
  - ID (some hashing algorithm, automatic).
  - Title (max 50 characters).
  - Description (max 256 characters).
  - Started (automatic Date.now()).
  - Deadline (optional).
- Tasks must be saved in a file with some format.
- A separate task may be searched.
- All expired tasks may be searched.

## Background music

- Just start some BGM in the beginning.
- Must be looped programatically.
- Don't forget to close, if necessary.