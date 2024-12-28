# Dockerized Python App

When you deploy Lambda functions using .zip files, the deployment package size is limited to 250 MB unzipped or 50 MB zipped. This is considered a hard limit and cannot be increased.

If your deployment package exceeds this limit, you can use container images to deploy your Lambda functions. Container images allow you to deploy Lambda functions with larger deployment packages, up to 10 GB.

The following requirements must be met when creating a container image for a Lambda function:

- Implement the Lambda runtime API.
- Based on Linux.
- Able to execute a read-only file system.
- Configured to access a `/tmp` directory with at least 512 MB of storage.
- Stored in an ECR private repository.

AWS Base Images are available for Lambda functions that include the Lambda runtime API. These images are based on Amazon Linux 2 and are optimized for Lambda functions. This lab will use the AWS Lambda Python 3.13 base image. 

More details
* https://docs.aws.amazon.com/lambda/latest/dg/images-create.html
* https://docs.aws.amazon.com/lambda/latest/dg/python-image.html#python-image-instructions

## Dockerize and test locally

```bash
# Build
docker build --platform linux/amd64 -t hello-lambda:v1 .
# When use with docker buildx
docker build --platform linux/amd64 --provenance=false -t hello-lambda:v1 .


# Run locally
docker run --platform linux/amd64 -p 9000:8080 hello-lambda:v1 .

# Test with curl
curl "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{}'
```

## Push to ECR
```bash
# Provission the ECR
aws ecr create-repository --repository-name hello-lambda --region us-east-1

# authenticate the Docker CLI to your Amazon ECR registry
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 637423476564.dkr.ecr.us-east-1.amazonaws.com

# Tag and push
docker tag hello-lambda:v1 637423476564.dkr.ecr.us-east-1.amazonaws.com/hello-lambda:v1
```
## Provission Lambda Function

https://docs.aws.amazon.com/lambda/latest/dg/python-image.html#python-image-base
First Create a Lambda role with basic permissions from here ../lambda_role
Role name: hello-lambda-role

```bash
aws lambda create-function \
  --function-name hello-lambda \
  --package-type Image \
  --code ImageUri=637423476564.dkr.ecr.us-east-1.amazonaws.com/hello-lambda:latest \
  --role arn:aws:iam::637423476564:role/hello-lambda-role

# Got an error
# The image manifest or layer media type for the source image 637423476564.dkr.ecr.us-east-1.amazonaws.com/hello-lambda:test is not supported.

# Validated
aws ecr describe-images --repository-name hello-lambda --image-ids imageTag=latest | jq
# .... application/vnd.oci.image.index.v1+json, 
# Solution from Stack Overflow: https://stackoverflow.com/questions/65608802/cant-deploy-container-image-to-lambda-function
# To fix this problem set --provenance=false to docker build.
# Then I did and works

# Invoke the function
aws lambda invoke --function-name hello-lambda response.json

```
