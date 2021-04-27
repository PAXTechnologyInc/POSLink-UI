#!/bin/bash

[ ! -f version.properties ] && echo "::error ::version.properties does not exist" && exit
source version.properties

[ -z "${_moduleVersion}" ] && echo "::error ::cannot find _moduleVersion" && exit

_DATE=$(TZ=America/New_York date '+%Y%m%d')
_TAG_NAME="v${_moduleVersion}-${_DATE}"

declare -i retry=0
_COMMITISH=${_TAG_NAME}

git fetch --tags
while :; do
  _RESULT=$(git tag -l ${_COMMITISH})
  # git describe returns fatal message if it found nothing
  if [[ "${_RESULT}" != "${_COMMITISH}" ]]; then
    break
  fi
  retry=$(( retry + 1 ))
  _COMMITISH=${_TAG_NAME}-$(printf %02d ${retry})
done

# set github action step output
echo ${$_COMMITISH}
echo "::set-output name=tag_name::$_COMMITISH"