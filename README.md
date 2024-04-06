# Microservices Projesi

Bu proje, Java ve Spring Boot kullanılarak geliştirilmiş, birden çok mikro servisin bir arada çalıştığı bir yapıyı temsil eder. Mikro servis mimarisi, projenin modüler ve kolayca ölçeklenebilir olmasını sağlar.

## Projenin Genel Yapısı

Proje, aşağıdaki mikro servislerden oluşmaktadır:

- **Order Service**: Müşteri siparişlerini yönetir. Siparişlerin durumunu takip eder ve sipariş işlemlerini gerçekleştirir.
- **Product Service**: Ürün kataloğunu yönetir. Yeni ürünlerin eklenmesi, mevcut ürünlerin listelenmesi gibi işlemleri sağlar.
- **Inventory Service**: Ürün stoklarını yönetir. Ürünlerin stok durumlarını kontrol eder ve stok güncellemelerini yapar.
- **API Gateway**: Tüm servisler arasında bir ara katman görevi görür. İstekleri ilgili mikro servislere yönlendirir ve sistem genelindeki güvenlik, yük dengeleme gibi işlemleri yönetir.
- **Discovery Server**: Mikro servislerin birbirini keşfetmesini ve iletişim kurmasını sağlayan bir Eureka Discovery Server'dır.

## Kurulum ve Çalıştırma

### Gereksinimler

- **Java 11**: Uygulamanın çalıştırılabilmesi için Java 11 sürümüne ihtiyacınız vardır.
- **Maven**: Bağımlılıkların yönetimi ve uygulamanın derlenmesi için Maven kullanılmaktadır.
- **Docker**: MongoDB ve KeyCloak için Docker container'ları kullanılmaktadır.

### Adımlar

1. **MongoDB Container'ını Çalıştırma**: Testler için MongoDB container'ını aşağıdaki komut ile çalıştırabilirsiniz:
docker run --name mongodb -d mongo:latest

2. **KeyCloak Kurulumu**: KeyCloak için Docker'da aşağıdaki komut ile 8181 portunda bir container başlatın:
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev

3. **Mikro Servisleri Çalıştırma**: Her bir mikro servisin kök dizininde aşağıdaki Maven komutu ile uygulamayı çalıştırın:
mvn spring-boot:run


## Testler

Her mikro servisin kendi birim testleri bulunmaktadır. Testleri çalıştırmak için her mikro servisin kök dizininde aşağıdaki Maven komutunu kullanın:
mvn test


## NOTLARIM
Java Spring Boot ile geliştirilen uygulamalarda, uygulama performansını izlemek, hataları tespit etmek ve sistemdeki veri akışını anlamak gibi ihtiyaçlar için çeşitli araçlar kullanılır. OpenZipkin, bu amaçlarla kullanılan açık kaynak kodlu bir dağıtık izleme sistemidir.

OpenZipkin, Twitter tarafından geliştirilmiş ve daha sonra açık kaynak olarak sunulmuştur. Temel amacı, mikroservis mimarisine sahip sistemlerdeki isteklerin izlenmesini sağlayarak, sistemdeki gecikmelerin ve olası hata noktalarının kolayca tespit edilmesine yardımcı olmaktır. Bu, özellikle büyük ve karmaşık mikroservis altyapılarında önemli bir ihtiyaçtır.

**Temel Özellikleri ve İşleyişi:**
Dağıtık İzleme (Distributed Tracing): Mikroservis mimarilerinde bir isteğin birden fazla servisi geçerek tamamlanması yaygındır. OpenZipkin, bir isteğin tüm yolculuğunu izleyerek, hangi servislerin ne kadar süre aldığını ve sistemin hangi noktalarında gecikmelerin yaşandığını gösterir.

1. **Zipkin UI:** Kullanıcı dostu bir arayüze sahip olan Zipkin, izleme verilerini görselleştiren bir web arayüzü sunar. Bu arayüz üzerinden isteklerin detaylarına, sürelerine ve hata bilgilerine kolayca erişilebilir.

2. **Esnek Veri Depolama:** Zipkin, izleme verilerini saklamak için farklı veri depolama çözümlerini destekler. Cassandra, Elasticsearch ve MySQL gibi popüler veri depolama sistemleriyle entegre çalışabilir.

3. **Hafif İstemci Kütüphaneleri:** OpenZipkin, Java Spring Boot başta olmak üzere farklı programlama dilleri ve çerçeveleri için izleme istemcileri (tracer) sunar. Bu kütüphaneler, uygulamalara kolayca entegre edilebilir ve minimal bir performans maliyeti ile izleme işlevselliği sağlar.

**Nasıl Çalışır?**
OpenZipkin'in çalışma prensibi, her bir mikroservis isteğinin benzersiz bir "trace ID" ile izlenmesine dayanır. Bu ID, isteğin başlangıcından sonuna kadar tüm servislerde taşınır. Böylece, bir isteğin hangi servislerden geçtiği, her serviste ne kadar süre harcadığı ve olası hata noktaları detaylı bir şekilde izlenebilir.

OpenZipkin, sisteminizdeki veri akışını ve performansı anlamak, bottlenecks (darboğazlar) ve hataları tespit etmek için güçlü bir araçtır. Java Spring Boot ile geliştirilen uygulamalarınızda kolayca entegre edilebilir, ve sistem sağlığının sürekli olarak monitor edilmesine olanak tanır.