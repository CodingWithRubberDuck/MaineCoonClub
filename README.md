Der er implementeret registrering, login, startside, startside efter login, 
og 'tilføj kat' siden efter login. (Man kan dog ikke tilgå listen af katte endnu, medmindre man tilgår MySQL direkte)

Derudover er der skrevet backend til at se alle medlemmer samt at søge efter medlem baseret på navn, men er ikke blevet implementeret i UI til at kunne interegere med det i brugergrænsefladen (endnu).


Til test kan du enten oprette eget login og dermed oprette dig som medlem eller også kan du bruge den allerede oprettede konto nedenunder 

(hvis .sql filerne kører som de skal):
Email = mikkel@gmail.com
Kodeord = mikkel1234






En application.properties fil skal indsættes i resources med indholdet:

spring.application.name=MaineCoonClub

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/maine_coon_club

spring.datasource.username=root

spring.datasource.password=root

spring.sql.init.mode=always


