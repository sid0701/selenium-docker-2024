#!/bin/bash

#-------------------------------------------------------------------
#  This script expects the following environment variables
#     HUB_HOST
#     BROWSER
#     THREAD_COUNT
#     TEST_SUITE
#-------------------------------------------------------------------

# Let's print what we have received

echo "---Env Variables---"
echo "HUB_HOST = ${HUB_HOST}"
echo "BROWSER = ${BROWSER}"
echo "THREAD_COUNT = ${THREAD_COUNT}"
echo "TEST_SUITE = ${TEST_SUITE}"
echo "----------"

echo "----Checking if the hub is ready---"
count=0
while [ "$(curl -s http://${HUB_HOST:-hub}/status | jq -r .value.ready)" != true ];
do
  count=$((count+1))
  echo "Attempt : ${count}"
  if [ "$count" -ge 30 ];
   then
     echo "---Hub was not ready within 30 seconds. Hence, quitting..."
     exit 1
  fi
  sleep 1
done

echo "---Selenium Grid is up and running..."

java -cp 'libs/*' \
     -Dselenium.grid.enabled=true \
     -Dselenium.grid.hubHost="${HUB_HOST:-hub}" \
     -Dbrowser="${BROWSER:-chrome}" \
     org.testng.TestNG \
     -threadcount "${THREAD_COUNT:-1}" \
     test-suites/"${TEST_SUITE}"