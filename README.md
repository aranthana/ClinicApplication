###ClinicApplication



#Prerequisites:
	- Maven 3.1+
	- npm
	
#Step 1:
		Download and unzip the source repository for this guide, or clone it using Git:
		
		>>> git clone https://github.com/aranthana/ClinicApplication.git
		
		
#Step 2:
	Build 'common-models' project
	
	>>> mvn clean install
	
#Step 3:
	Build and Run 'clinic-patient-management'
	
	>>>> mvn spring-boot:run
	
	Application will be running on localhost:8080
	
	
#Step 4:
	Build and Run 'clinic-patient-billing'
	
	>>>> mvn spring-boot:run
	
	Application will be running on localhost:9097
	
#Step 5:
	Build dependencies and React Application 'clinic-app'
	
	>>>> npm install
	
	
	Start "clinic-app'
	
	>>>> npm start
	
	Application will be running on localhost:3000
	