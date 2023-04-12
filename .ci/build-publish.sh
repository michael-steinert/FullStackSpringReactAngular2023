: "${USERNAME:?USERNAME not set or empty}"
: "${REPOSITORY:?REPOSITORY not set or empty}"
: "${TAG:?TAG not set or empty}"

docker buildx create --use

docker buildx build \
    --platform=linux/arm64 \
    -t "${USERNAME}/${REPOSITORY}:${TAG}" \
    -t "${USERNAME}/${REPOSITORY}:latest" \
     # Second Argument of Command i.e. Build Arguments
    "${@:2}" \
    --push \
    # First Argument of Command i.e. Path of Script
    "$1"