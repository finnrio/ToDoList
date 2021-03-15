# To Do List
Read me for ToDoList personal project.
## Jenkins set up
Jenkins is running locally on my mac on port 8080 with jenkins-lts. To start type `jenkins-lts` in the terminal. <br>
The webhook was created with relay. <br>
Get the credentials from [Here](https://my.webhookrelay.com/tokens) <br>
Start up relay in the terminal with this command `relay login -k THE-KEY-FROM-LINK-ABOVE -s THE-SECRET-FROM-LINK-ABOVE` 
<br>
Once logged in, type this command `relay fordward --bucket github-jenkins http://localhost:8080/github-webhook/` <br>
This will start up a secure webhook and provide a https address to enter to the github webhook URL.
<<<<<<< HEAD
trying s
=======
>>>>>>> 3833cc21f9dc07ffdd5bec7adf52efbf065711b9
