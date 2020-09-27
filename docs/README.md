# User Guide
Duke is a chatbot, capable of managing the user's schedule.

## Features

Please go through the features in a sequential order as the example task list evolves through these features.

### 1. Greet User 
Duke always greets its user before managing the user's schedule.

#### Usage

No instruction is required! Duke will always greet the user everytime it is called.

Expected outcome:

`Hey it's your favorite chatbot Duke!`\
`How can I assist you today?`
 
 
### 2. Inform User When No Prior List is Saved
Duke will let the user know when there is no previously saved list on the hard drive.
 
#### Usage
 
No instruction is required! Duke will let the user know when it is called.
 
Expected outcome (if no previously saved list):
 
`No old tasklist found time to start fresh!`
 
 
### 3. Add a Todo
Duke allows the user to add a todo task.
  
#### Usage
  
Format of usage: 
    
`todo [task]`
  
Example of usage: 
  
`todo read book`
  
Expected outcome:
  
`Okay! I have added this:`\
`[T][✘] read book`\
`Now you have 1 task in the list.`
  
  
### 3. Add a Deadline
Duke allows the user to add a deadline task.
  
#### Usage
  
Format of usage: 
    
`deadline [task] /by [date]`
  
Example of usage: 
  
`deadline return book /by 29th Sept 2pm`
  
Expected outcome:
  
`Okay! I have added this:`\
`[D][✘] return book (by: 29th Sept 2pm)`\
`Now you have 2 tasks in the list.`


### 4. Add an Event
Duke allows the user to add an event task.
  
#### Usage
  
Format of usage: 
    
`deadline [event] /by [date]`
  
Example of usage: 
  
`event team presentation /at 30th Sept 5-6:30pm`
          
Expected outcome:
        
`Okay! I have added this:`\
`[E][✘] team presentation (at: 30th Sept 5-6:30pm)`\
`Now you have 3 tasks in the list.`
  
  
### 5. View Task List
Duke allows the user to view all the tasks at once.
  
#### Usage
  
Format of usage: 
      
`list`
  
  
Expected outcome:

`Here's your TASK LIST!`\
`1.[T][✘] read book`\
`2.[D][✘] return book (by: 29th Sept 2pm)`\
`3.[E][✘] team presentation (at: 30th Sept 5-6:30pm)`
  
  
### 6. Mark Task as Done
Duke allows the user to mark a task as done.
  
#### Usage
  
Format of usage: 
      
`done [task number]`
    
Example of usage: 
    
`done 2`
  
Expected outcome:

`I have marked your task as done!`\
`[D][✓] return book (by: 29th Sept 2pm)`


### 7. Delete a Task
Duke allows the user to remove a task from the task list.
  
#### Usage
  
Format of usage: 
      
`delete [task number]`
    
Example of usage: 
    
`delete 1`
  
Expected outcome:

`I have deleted this task!`\
`[T][✘] read book`\
`Now you have 2 tasks in the list.`


### 8. Find a Task
Duke allows the user to find tasks based on a keyword.
  
#### Usage
  
Format of usage: 
      
`find [keyword]`
    
Example of usage: 
    
`find presentation`

Expected outcome:

`Here are the matching tasks in your list:`\
`1.[E][✘] team presentation (at: 30th Sept 5-6:30pm)`


### 9. Say Goodbye
Duke will say bye to the user and save all tasks for future purposes.
  
#### Usage
  
Format of usage: 
      
`bye`


Expected outcome:

`Bye see you SOON!`
