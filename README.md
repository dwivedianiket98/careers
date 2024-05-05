# careers
trying to create a demo project simulating a careers application

currently created:
* api for adding and getting candidate
* api for uploading and downloading candidate resume

required:
* login api
    * should be different for candidates and recruiters
* login page
* signup api


----------------------------

this branch: 05-May-2024

* ~~users instead of candidates~~
* ~~user controller, service, repository~~

* ~~a user manager for user flow~~
* ~~will call user/create api for creating candidate or a recruiter~~
* ~~based on the role passed in the request will create either a candidate or a recruiter~~

* ~~change the endpoint~~
* ~~create usermanager~~
* ~~put the logic for creating recruiter or candidate in the usermanager~~

--------------------------------

up next:

* security
  * login and signup
  * password encryption for storage
  * role based access
  * token for authentication and authorization
* Job flow
  * job manager for job flow control
  * posting job
  * applying to a job
  * editing and changing status