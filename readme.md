# Online Store 🛒
The project is summarized for modules 3-4 in Java. In this project, we focus on creating an online store. There are several important features that our store offers.

### Goal

The main goal of our project is to provide users the ability to make online purchases. These purchases include a product catalog, cart functionality, and notification messages.

### Features

- Product Catalog: Users have the ability to view, search, and select from the product catalog.
- Cart: Ability to add, remove, and view products in the cart. Users can make payments for their cart.
- Archive: All products purchased by the users are stored in the archive.
- Notification: Users can read messages and admin can send messages.
- Delete Account: Allows users to delete their accounts.
- Block/Unblock: Admin has the ability to block and unblock users.

````java
private void addMessageToUser(String userId, String message) {
    NotificationMessage userMessage = findOrCreateUserMessage(userId);
    String dateTime = simpleDateFormatThreadLocal.get().format(new Date());
    String alert = "Send notification to user with ID: " + userId + "nMessage: " + message + "nSent at: " + dateTime + "n------------------------------";
    userMessage.add(alert);
}

private NotificationMessage findOrCreateUserMessage(String userId) {
    return notificationMessages.stream()
                 .filter(message -> message.getId().equals(userId))
                 .findFirst()
                 .orElseGet(() -> {
                     NotificationMessage newUserMessage = new NotificationMessage(userId);
                     notificationMessages.add(newUserMessage);
                     return newUserMessage;
                 });
}
````

### Using the Program
- 1 Go to the main Web class and press the run button or (CONTROL+R)
- 2 Through the console, choose the required method SIGN-IN or SIGN-UP
- 3 For SIGN-IN, you can select the username and password from the users in the DefaultData class.
