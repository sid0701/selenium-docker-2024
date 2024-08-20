echo "----Checking if the hub is ready---"
count=0
while [ "$(curl -s http://${HUB_HOST:-hub}/status | jq -r .value.ready)" != true ];
do
  count=$((count+1))
  echo "Attempt : ${count}"
  if [ "$count" -ge 30 ];
   then
     echo "---Hub was not ready within 30 seconds. Hence, quitting..."
     exit 1;
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