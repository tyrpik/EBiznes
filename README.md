# Zadanie 1 Docker


3.0 obraz ubuntu z Pythonem w wersji 3.10 https://github.com/tyrpik/EBiznes/tree/main/zad1Docker/task_3_0

3.5 obraz ubuntu:24.04 z Javą w wersji 8 oraz Kotlinem https://github.com/tyrpik/EBiznes/tree/main/zad1Docker/task_3_5

4.0 do powyższego należy dodać najnowszego Gradle’a oraz paczkę JDBC SQLite w ramach projektu na Gradle (build.gradle) https://github.com/tyrpik/EBiznes/tree/main/zad1Docker/task_4_0

4.5 stworzyć przykład typu HelloWorld oraz uruchomienie aplikacji przez CMD oraz gradle https://github.com/tyrpik/EBiznes/tree/main/zad1Docker/task_4_5

5.0 dodać konfigurację docker-compose https://github.com/tyrpik/EBiznes/tree/main/zad1Docker/task_5_0


kod do zadania 1: https://github.com/tyrpik/EBiznes/tree/main/zad1Docker

video: https://github.com/tyrpik/EBiznes/blob/main/zad1Docker/docker.mkv

# Zadanie 2 Scala

3.0 Należy stworzyć kontroler do Produktów https://github.com/tyrpik/EBiznes/blob/main/zad2Scala/app/controllers/ProductController.scala

3.5 Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane pobierane z listy https://github.com/tyrpik/EBiznes/blob/main/zad2Scala/app/controllers/ProductController.scala

4.0 Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy zgodnie z CRUD https://github.com/tyrpik/EBiznes/tree/main/zad2Scala/app/controllers

kod do zadania 2: https://github.com/tyrpik/EBiznes/tree/main/zad2Scala

video: https://drive.google.com/file/d/1DkjGpl3Pfqolreaq34vbyjQ2Sb2Rb3qy/view?usp=drive_link

# Zadanie 3 Kotlin

3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord: 
https://github.com/tyrpik/EBiznes/blob/main/zad3Kotlin/discord-client/src/main/kotlin/Application.kt

kod do zadania 3: https://github.com/tyrpik/EBiznes/blob/main/zad3Kotlin

video: https://github.com/tyrpik/EBiznes/blob/main/zad3Kotlin/kotlin.mkv

# Zadanie 4 Go

Należy stworzyć projekt w echo w Go. Należy wykorzystać gorm do stworzenia kilka modeli, gdzie pomiędzy dwoma musi być relacja. Należy zaimplementować proste endpointy do dodawania oraz wyświetlania danych za pomocą modeli. Jako bazę danych można wybrać dowolną, sugerowałbym jednak pozostać przy sqlite.

3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Produktów zgodny z CRUD: https://github.com/tyrpik/EBiznes/blob/main/zad4Go/main.go

3.5 Należy stworzyć model Produktów wykorzystując gorm oraz wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast listy): https://github.com/tyrpik/EBiznes/tree/main/zad4Go/models https://github.com/tyrpik/EBiznes/blob/main/zad4Go/controllers/product_controller.go

4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint: https://github.com/tyrpik/EBiznes/tree/main/zad4Go/models https://github.com/tyrpik/EBiznes/blob/main/zad4Go/controllers/cart_controler.go

4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem: https://github.com/tyrpik/EBiznes/tree/main/zad4Go/models https://github.com/tyrpik/EBiznes/blob/main/zad4Go/controllers/category_controller.go

5.0 pogrupować zapytania w gorm’owe scope'y: https://github.com/tyrpik/EBiznes/blob/main/zad4Go/models/models.go https://github.com/tyrpik/EBiznes/blob/main/zad4Go/controllers/product_controller.go

kod do zadania 4: https://github.com/tyrpik/EBiznes/tree/main/zad4Go

video: https://github.com/tyrpik/EBiznes/blob/main/zad4Go/go.mkv

# Zadanie 5 Frontend

Należy stworzyć aplikację kliencką wykorzystując bibliotekę React.js. W ramach projektu należy stworzyć trzy komponenty: Produkty, Koszyk oraz Płatności. Koszyk oraz Płatności powinny wysyłać do aplikacji serwerowej dane, a w Produktach powinniśmy pobierać dane o produktach z aplikacji serwerowej. Aplikacja serwera w jednym z trzech języków: Kotlin, Scala, Go. Dane pomiędzy wszystkimi komponentami powinny być przesyłane za pomocą React hooks.

3.0 W ramach projektu należy stworzyć dwa komponenty: Produkty oraz Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w Produktach powinniśmy pobierać dane o produktach z aplikacji serwerowej: https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Produkty.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Platnosci.js

3.5 Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing: https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Koszyk.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/App.js

4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za pomocą React hooks: https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/CartContext.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Produkty.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Platnosci.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Koszyk.js 

4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz kliencką na dockerze via docker-compose: https://github.com/tyrpik/EBiznes/blob/main/zad5React/docker-compose.yml https://github.com/tyrpik/EBiznes/blob/main/zad5React/backend/Dockerfile https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/Dockerfile

5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS: https://github.com/tyrpik/EBiznes/blob/main/zad5React/backend/main.go https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Produkty.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Platnosci.js https://github.com/tyrpik/EBiznes/blob/main/zad5React/frontend/src/Koszyk.js

kod do zadania 5: https://github.com/tyrpik/EBiznes/tree/main/zad5React

video: https://github.com/tyrpik/EBiznes/blob/main/zad5React/react.mkv

# Zadanie 6 Testy
Należy stworzyć 20 przypadków testowych w jednym z rozwiązań:

- Cypress JS (JS)
- Selenium (Kotlin, Python, Java, JS, Go, Scala)

Testy mają w sumie zawierać minimum 50 asercji (3.5). Mają również
uruchamiać się na platformie Browserstack (5.0). Proszę pamiętać o
stworzeniu darmowego konta via https://education.github.com/pack.

3.0 Należy stworzyć 20 przypadków testowych w CypressJS lub Selenium
(Kotlin, Python, Java, JS, Go, Scala): https://github.com/tyrpik/EBiznes/blob/main/zad6Testy/conftest.py

kod do zadania 6: https://github.com/tyrpik/EBiznes/tree/main/zad6Testy

video: https://github.com/tyrpik/EBiznes/blob/main/zad6Testy/testy.mkv

# Zad 7 Sonar
Należy dodać projekt aplikacji klienckiej oraz serwerowej (jeden
branch, dwa repozytoria) do Sonara w wersji chmurowej
(https://sonarcloud.io/). Należy poprawić aplikacje uzyskując 0 bugów,
0 zapaszków, 0 podatności, 0 błędów bezpieczeństwa. Dodatkowo należy
dodać widżety sonarowe do README w repozytorium dane projektu z
wynikami.

3.0 Należy dodać litera do odpowiedniego kodu aplikacji serwerowej w
hookach gita: https://github.com/tyrpik/EBiznes/tree/main/zad7Sonar https://github.com/tyrpik/sonar-client/blob/main/README.md 

3.5 Należy wyeliminować wszystkie bugi w kodzie w Sonarze (kod
aplikacji serwerowej): https://github.com/tyrpik/EBiznes/tree/main/zad7Sonar

4.0 Należy wyeliminować wszystkie zapaszki w kodzie w Sonarze (kod
aplikacji serwerowej): https://github.com/tyrpik/EBiznes/tree/main/zad7Sonar

4.5 Należy wyeliminować wszystkie podatności oraz błędy bezpieczeństwa
w kodzie w Sonarze (kod aplikacji serwerowej)
aplikacji klienckiej: https://github.com/tyrpik/EBiznes/tree/main/zad7Sonar

kod do zadania 7: https://github.com/tyrpik/EBiznes/tree/main/zad7Sonar

wideo: https://github.com/tyrpik/EBiznes/blob/main/zad7Sonar/sonar.mkv

# Zad 8 Oauth
Należy skonfigurować klienta Oauth2 (4.0). Dane o użytkowniku wraz z
tokenem powinny być przechowywane po stronie bazy serwera, a nowy
token (inny niż ten od dostawcy) powinien zostać wysłany do klienta
(React). Można zastosować mechanizm sesji lub inny dowolny (5.0).
Zabronione jest tworzenie klientów bezpośrednio po stronie React'a
wyłączając z komunikacji aplikację serwerową.

Prawidłowa komunikacja: react-sewer-dostawca-serwer(via return
uri)-react.

3.0 logowanie przez aplikację serwerową (bez Oauth2): https://github.com/tyrpik/EBiznes/blob/main/zad8Oauth/server/server.js https://github.com/tyrpik/EBiznes/blob/main/zad8Oauth/client/src/App.js

3.5 rejestracja przez aplikację serwerową (bez Oauth2): https://github.com/tyrpik/EBiznes/blob/main/zad8Oauth/server/server.js https://github.com/tyrpik/EBiznes/blob/main/zad8Oauth/client/src/App.js

kod do zadania 8: https://github.com/tyrpik/EBiznes/tree/main/zad8Oauth

wideo: https://github.com/tyrpik/EBiznes/blob/main/zad8Oauth/oauth.mkv

# Zad 9 GPT
Należy rozszerzyć funkcjonalność wcześniej stworzonego bota. Do niego
należy stworzyć aplikację frontendową, która połączy się z osobnym
serwisem, który przeanalizuje tekst od użytkownika i prześle zapytanie
do GPT, a następnie prześle odpowiedź do użytkownika. Cały projekt
należy stworzyć w Pythonie.

3.0 należy stworzyć po stronie serwerowej osobny serwis do łącznia z
chatGPT: https://github.com/tyrpik/EBiznes/blob/main/zad9Gpt/main.py

Można wykorzystać lokalny model przez ollama (https://ollama.com/).

kod do zadania 9: https://github.com/tyrpik/EBiznes/tree/main/zad9Gpt

wideo: https://github.com/tyrpik/EBiznes/blob/main/zad9Gpt/ollama.mkv

# Zad 10 Chmura

3.0 Należy stworzyć odpowiednie instancje po stronie chmury na
dockerze: https://github.com/tyrpik/EBiznes/blob/main/zad10Chmura/Dockerfile https://github.com/tyrpik/EBiznes/blob/main/zad10Chmura/docker-compose.yml

3.5 Stworzyć odpowiedni pipeline w Github Actions do budowania
aplikacji (np. via fatjar): https://github.com/tyrpik/EBiznes/blob/main/.github/workflows/ci.yml

4.0 Dodać notyfikację mailową o wynikach z sonara: https://github.com/tyrpik/EBiznes/blob/main/.github/workflows/ci.yml https://github.com/tyrpik/EBiznes/blob/main/zad10Chmura/sonar-project.properties

kod do zadania: https://github.com/tyrpik/EBiznes/tree/main/zad10Chmura

wideo: https://github.com/tyrpik/EBiznes/blob/main/zad10Chmura/chmura_ebiz.mkv




