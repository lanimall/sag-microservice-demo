# sag-microservice-demo

A small demo showing how to build microservices with webMethods Microservice Container, 
using the built-in Consul integration for service discovery, circuit-breaker pattern for self-healing, and docker-swarm for self-scaling

The demo starts a Consul cluster (3 Consul servers, 3 Consul Agents) and 4 Microservice Container, each with their own REST endpoints.

## Prerequisites

1. This demo uses Docker, so you'll need it installed.
   Check official Docker doc https://docs.docker.com/install/ for details.

2. This demo relies on the SoftwareAG Microservice Container localted in Docker Store.

   Go to https://store.docker.com/images/softwareag-webmethods-microservicescontainer,
login with your Docker Store Credetials, 
and click "proceed to checkout" button.

   Once you have filled out the info and accepted the agreement, you should be able to pull the image to your workstation, as follows:

   ```
   docker login
   docker pull store/softwareag/webmethods-microservicescontainer:10.1
   ```
3. License 

   The SoftwareAG Microservice Container docker image comes bundled with a time-limited trial license.
   So you shoud be able to run this demo AS-IS without any issues.
   
   But if you are a current customer, and have an actual license, 
   and don't want to see the "License Key is Expired or Invalid." at the top of webMethods Administration UI,
   you can also apply it by doing the following:
   
   1. Copying it in the root of this directory and naming it "licenseKey.xml"
   2. Edit the file ./Dockerfile.msc.custom
   3. Uncomment the line 6 that starts with "COPY licenseKey.xml"

## Run the demo

Build the docker images:

```
docker-compose -f docker-compose-build.yml build
```

Run the project:

```
docker-compose up -d
```

## Demo Microservices

Consul UI: http://localhost:8500/ui/
In the UI, you should see the microservices getting registered as they come up.

The demo wM microservices are defined as follow:

- IP locator service
  - Endpoint: http://localhost:5557/restv2/iplocator/{ipv4}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5557/restv2/iplocator/208.80.152.201
  ```
  
- User Info service (mock a DB call by returning random user data)
  - Endpoint: http://localhost:5558/restv2/userinfo/{userId:someAlphaNumericalValue - Or empty -- will generate something random for you}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5558/restv2/userinfo/
  ```
  
- Fibonacci serie
  - Endpoint: http://localhost:5559/restv2/NextNumber/{positive number: index in the serie}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5559/restv2/NextNumber/123
  ```
  
- Macro User Info Service that aggregates the User Info + IP location of the user
  - Endpoint: http://localhost:5556/restv2/userdetails/{userId:someAlphaNumericalValue}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5556/restv2/userdetails/
  ```
  
Now, check the Consul UI: http://localhost:8500/ui/
In there, you should see the microservices all registered as they come up.


## Looking under the hood

To see and possibly modify the actual microservices from your webmethods Designer, run the DEV compose.

Note: if you're still running the demo above, turn it off first:

```
docker-compose down
```

Then, run:

```
docker-compose -f docker-compose-dev.yml up -d
```

That will start 1 Microservice container (accessible at http://localhost:5555) to which you can connect your webmethods Designer,
like you would for any other Integration Server.

From there, copy the 4 packages in ./wMPackages to your local ./replicate/inbound/ folder, 
which is mapped (via Docker volume) to the actual wM IS Inbound folder in the docker instance.

And finally, connect to wM Web Admin UI (http://localhost:5555), login with "Administrator:manage", and import the 4 packages, 
like you would for any other Integration Server:
 - Go to "Package" > "Management"
 - Click "Install Inbound Releases"
 - In the dropdown, you should see all 4 packages
 - Click the "Install Release" button for each of them

Now, all 4 package should be installed and running, with all endpoints accessible on http://localhost:5555/ this time 
(because they are all on the same Integration Server)

- IP locator service
  - Endpoint: http://localhost:5555/restv2/iplocator/{ipv4}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5555/restv2/iplocator/208.80.152.201
  ```

- User Info service (mock a DB call by returning random user data)
  - Endpoint: http://localhost:5555/restv2/userinfo/{userId:someAlphaNumericalValue - Or empty -- will generate something random for you}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5555/restv2/userinfo/
  ```

- Fibonacci serie
  - Endpoint: http://localhost:5555/restv2/NextNumber/{positive number: index in the serie}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5555/restv2/NextNumber/123
  ```

- Macro User Info Service that aggregates the User Info + IP location of the user
  - Endpoint: http://localhost:5555/restv2/userdetails/{userId:someAlphaNumericalValue}
  - Example:
  ```
  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Administrator:manage -X GET http://localhost:5555/restv2/userdetails/
  ```