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
docker run -p 8181:8080 jboss/keycloak:17

3. **Mikro Servisleri Çalıştırma**: Her bir mikro servisin kök dizininde aşağıdaki Maven komutu ile uygulamayı çalıştırın:
mvn spring-boot:run


## Testler

Her mikro servisin kendi birim testleri bulunmaktadır. Testleri çalıştırmak için her mikro servisin kök dizininde aşağıdaki Maven komutunu kullanın:
mvn test


Bu yapı, projenizin genel bir özetini sunarken, kurulum ve çalıştırma adımlarını da detaylı bir şekilde açıklar. Böylece, projenize yeni başlayan birisi bile kolayca başlayabilir ve projenizi anlayabilir.