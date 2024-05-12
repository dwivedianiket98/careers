# careers
trying to create a demo project simulating a careers application

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

**User Creation Request**
```
curl --location 'http://localhost:8080/user/register' \
--form 'name="Travis"' \
--form 'email="thead@gmail.com"' \
--form 'password="thead"' \
--form 'roleId="2"' \
--form 'address="aussie man"' \
--form 'resume=@"/C:/Users/HP/Downloads/Resume.pdf"' \
--form 'age="28"'
```

--------------------------------

up next:

* security
  * login and signup
  * password encryption for storage
  * role based access
  * token for authentication and authorization


------------------------------------------
* Job flow
  * job manager for job flow control
  * posting job
  * applying to a job
  * editing and changing status