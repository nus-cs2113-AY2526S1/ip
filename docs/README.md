# Zoro Task Manager - User Guide

*"Listen up! Here's how you manage tasks like a proper swordsman."*

## Getting Started

Run the application and you'll see the main menu. Type **2** to start managing tasks, or **1** to test the echo feature.

```
What can I do for you?
1: echo - I'll repeat what you say 
2: task - Let's get your schedule sharper than my blades
```

## Core Task Operations

### Creating Tasks

**Basic Task (Todo)**
```
todo practice meditation
```
Perfect for simple tasks without time constraints.

**Task with Deadline**
```
deadline finish project report /by Friday 5pm
```
Use when you have a specific due date or time limit.

**Scheduled Event**
```
event team meeting /from Monday 2pm /to Monday 3pm
```
For appointments, meetings, or time-blocked activities.

### Managing Your Tasks

**See All Tasks**
```
list
```
Shows your complete task list with checkboxes and details.

**Mark Complete/Incomplete**
```
mark 2
```
Toggle task #2 between done ✓ and not done. Use the number from your task list.

**Remove Tasks**
```
delete 1
```
Permanently removes task #1 from your list.

**Find Tasks**
```
find meeting
```
Shows all tasks containing the word "meeting".

## Quick Tips

- **Task numbers change** when you delete tasks - always check `list` first
- **Keywords matter**: Use `/by` for deadlines, `/from` and `/to` for events
- **Case sensitive**: Commands are lowercase, but task descriptions can be anything
- **Get lost?** Type `menu` to return to the main screen anytime

## Common Mistakes

**Wrong:** `deadline study for exam Friday`  
**Right:** `deadline study for exam /by Friday`

**Wrong:** `event gym workout 6pm 7pm`  
**Right:** `event gym workout /from 6pm /to 7pm`

**Wrong:** `mark finish homework`  
**Right:** `mark 3` (use the task number, not description)

## Example Workflow

```
# Start your day
todo review emails
deadline submit report /by today 5pm
event lunch meeting /from 12pm /to 1pm

# Check what you have
list

# Complete the first task
mark 1

# Later, clean up
delete 1
find report
```

## Navigation

- **menu** or **back** - Return to main menu
- **bye** - Exit application
- **list** - Always available in task mode

---

*Your tasks are automatically saved. Now get to work and become the greatest task-master!*