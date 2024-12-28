# Python App

## Deploy
1. In the AWS Management Console, type Lambda into the search bar at the top of the page, and then click on Lambda from the drop-down menu.
2. In the AWS Lambda console, click Create function in the top right.
3. On the Create function page, leave Author from scratch selected.
4. For Function name, type in the name `HelloFunction`.
5. Set the `Python` runtime version to `3.13`
6. Leave the other defaults
7. Click Create function at the bottom of the page.

Your Lambda function should now be created.

### Additional actions
Create a Test Event and Execute Lambda

1. In the Code source menu bar, click the down arrow next to Test, and click Configure test event.
2. In the Configure test event popup window, for the Event name, type `Test`.
3. Leave the other default values.
4. Click Save.

You have now created your test event.

To test the Lambda function, click Test again in the Code source menu bar.
1. Review the output response.
2. From Monitor tab, review the logs in CloudWatch, should be created a CloudWatch group `/aws/lambda/HelloFunction`

