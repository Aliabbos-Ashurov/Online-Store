# Online Store 🛒 
Project is summarized for modules 3-4 in java.In this project, we focus on creating an online store. There are several important features that our store serves.
### Maqsad

Proyektimizning asosiy maqsadi, foydalanuvchilarga onlayn xaridlar qilish imkoniyatini taqdim etishdir. Bu xaridlar, mahsulotlar katalogi, savatcha funktsiyasi va notification xabarlar.

### Funksiyalar

- **Product catalog**: Foydalanuvchilar mahsulotlar katalogini ko'rish, qidirish va tanlash imkoniyatiga egalar.
- **Cart**:Mahsulotlarni savatga qo'shish, o'chirish,ko'rish imkoniyati mavjud. Foydalanuvchilar savatcha uchun to'lovlarni amalga oshirishlari mumkin.
- **Archive** User sotib olgan barcha mahsulotlar archive da saqlanadi.
- **Notification** Foydalanuvchilar xabarlarni o'qishlari va admin xabar yuborishi mumkin
- **Delete Account** Foydalanuvchilar o'zlarning akkuntlarini o'chirishlari mumkin
- **Block/Unblock** Admin userlarni block va unblock qilish holatlari mavjud
```java
private void addMessageToUser(String userId, String message) {
    NotificationMessage userMessage = findOrCreateUserMessage(userId);
    String dateTime = simpleDateFormatThreadLocal.get().format(new Date());
    String alert = "Send notification to user with ID: " + userId + "\nMessage: " + message + "\nSent at: " + dateTime + "\n------------------------------";
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
 ```
### Dasturni ishlatish
- **1** Web asosiy klassiga o'tib run tugmasini bosish yoki (CONTROL+R)
- **2** Console orqali SIGN-IN yoki SIGN-UP kerakli methodni tanlash
- **3** SIGN-IN uchun DefaultData klassidagi userlar ni username va passwordni tanlash mumkin.

Github [Download project from github](https://github.com/Aliabbos-Ashurov).