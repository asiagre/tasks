#!/usr/bin/env bash

open_safari() {
  if open -a Safari http://localhost:8080/crud/v1/task/getTasks; then
    end
  else
    "Cannot open a browser"
    fail
  fi
}

fail() {
  echo "There were errors"
}

end() {
  echo "Browser is opened"
}

if sudo ./runcrud.sh; then
   open_safari
else
   fail
fi