echo "------------------------------------"
echo "HUB_HOST -> ${HUB_HOST:-hub}"
echo "BROWSER -> ${BROWSER:-chrome}"
echo "THREAD_COUNT -> ${THREAD_COUNT:-1}"
echo "HUB_HOST -> ${HUB_HOST:-hub}"
echo "------------------------------------"
echo "Checking if the hub is ready.."
count=0
while [ "$(curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready)" != "true" ]
do
  count=$((count+1))
  echo "Attempt : ${count}"
#  if [ "$count" -ge 30 ]
#  then
#    echo "-----HUB was not ready within 30 seconds. Thus exiting.."
#    exit 1
#  fi
  sleep 1
done

echo "Selenium Grid is up and running.."

java -cp 'libs/*' \
     -Dselenium.grid.enabled=true \
     -Dselenium.grid.hubHost="${HUB_HOST:-hub}" \
     -Dbrowser="${BROWSER:-chrome}" \
     org.testng.TestNG \
     -threadcount "${THREAD_COUNT:-1}" \
     test-suites/"${TEST_SUITE}"

