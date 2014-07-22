![alt tag](https://camo.githubusercontent.com/f70f169cab179f13f70e892b3251e6f2032be66d/687474703a2f2f6c69626764782e6261646c6f67696367616d65732e636f6d2f696d672f6c6f676f2e706e67)
![alt tag](https://www.peakgames.net/images/about_img.jpg)

# Peak Games - Bubble Struggle

**Oyunun Özellikleri :**

 * 3 adet can hakkı
 * Vurulan topa göre puan kazanımı
 * Büyük topun vurulunca 2 küçük topa ayrılması
 * Karakterin sağa sola hareketi
 * Karakterin ateş etmesi
 * Çeşitli ses efektleri
 * Çeşitli grafikler
 * Saniye barı ve animasyonu
 
 **Yön Tuşları**
 
 * Sağ - Sol yön tuşlarıyla oyuncuyu hareket ettirebilirsiniz.
 * Space tuşuyla toplara ateş edebilirsiniz.


**Bazı trickler :** 

* R harfine basılınca oyunun yeniden başlatılması
* A - D basılınca oyuncunun sol-sağ duvarın dibinde belirmesi
* Aşağı oka basılınca oyuncunun ortada belirmesi
* 0'a basılınca topun otomatik vurulması
* 1'e basılınca yeni topun oluşturulması
* 2'ye basılınca oyuncunun öldürülmesi
* 3'e basılınca bir sonraki level'e geçilmesi

>Yukarıdaki trickler eğlence amaçlı yapılmıştır. Oyunun senaryosunda bulunmamaktadır.

Oyunun kodlanma aşamasında genel itibariyle singleton pattern'i kullanılmıştır.

#Sınıflar

- **Objects**
 * Player
 * Ball
 * Bullet
 
- **Managers**
 * BallManager
 * GameManager
 * WorldManager
 
- **Render**
 * BubbleStruggle
 
 Player,Ball ve Bullet objeleri genel olarak oyunun sahnesinde bulunan ve etkileşim içerisinde olan özellikleri içerir.
 Managerlar ise bu objelerin dünya ile olan etkileşimini sağlarlar. Bu sayede daha soyut bir yapı oluşturularak yeni özelliklere daha uyumlu hale getirilmiştir.
 
**Kaynaklar:**

[Box 2d Tutorial](https://github.com/libgdx/libgdx/wiki/Box2d)

[LibGdx Setup for Eclipse](https://github.com/libgdx/libgdx/wiki/Project-setup%2C-running-%26-debugging)

[Stackoverflow](http://stackoverflow.com/search?q=libgdx)
 
 
 
 
 
