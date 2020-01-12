# currency-rates
[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/M4M61032L)

Currency rates analyzer

Backendui naudojau:
Eclipse JEE 2019-06
Java 8
Spring Boot
Thymeleaf
Maven
Lombok (!Reikalauja instaliacijos IDE)

Frontendui naudojau:
HTML + JavaScript + jQuery (tik dėl datos pickeriaus)
Testavau Safari ir Chrome

Paleidimas:
1.Užinstaliuoti Lombok atpažinimą IDE. (https://projectlombok.org/setup/eclipse). Mac’e papildomai reikės patvirtinti prieigą per Security & Privacy.
2.Eclipce -> Import -> Existing Maven projects -> Pasirinkti projektą.
3.Paleisti kaip Java Application klasę  CurrencyRatesApplication.java.
4.Browseryje atidaryti localhost:8080/
5.Testuoti

Testavimas:
1.Pasirinkti valiutq
2.Pasirinkti datą

Užklausos siunčiamos automatiškai po pakeitimų. Kursų skirtumas atvaizduojamas virš pasirinkimo. Jeigu pasirinktos datos patenkq į dienas kai kursas nebuvo publikuojamas - atvaizduojamas pranešimas.

Geriausiam testavimo experiensui siūlau testuoti su JAV dolerių, ar kita, dažnai publikuojama valiuta.
