# Covid-DB_Fullstack

Telechargez l'archive git avec cette commande

```git clone -b docker https://github.com/Cimmerian-Iter/Covid-DB_Fullstack.git```

mettez vous dans le repertoire 

```cd Covid-DB_Fullstack```

Lancez ```docker build``` puis ```docker compose up```

#Jenkins
Pour pouvoir utiliser jenkins, il faut d'abord avoir jenkins-compose disponible ici : ```git clone https://github.com/jredel/jenkins-compose```

Lancer ```cd jenkins-compose``` puis ```docker compose up```

Se connecter sur le site puis fournir le Jenkins file afin de pouvoir génerer la pipeline.

#Test

Ce repo possède un test.http, qui est un exemple de requete. Pour communiquer avec le serveur, pour l'instant il est possible de verifier l'authentification. En lançant la requète http, on doit avoir un code http 200. En enlevant la ligne Authorization, vous devirez recevoir un code HTTP 401.

#Erreur
Malheureusement, Nous avons essuyé beaucoup de problème avec la connexion entre la base de donnée et le backend. Pour des raisons dont on ignore.

Le commit final de cette branche à bien fonctionné, mais en relançant encore une fois sous un autre environnement le projet ne marche plus avec cette erreur : covid-db_fullstack-jdk-1  | org.postgresql.util.PSQLException: Connection to db:5432 refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.

Cette erreur est d'autant plus incompréhensible que nous avons correctement nommé les entités dans le docker-compose.yaml et l'application properties. Ainsi nous avons remplacer localhost par db comme défini dans le docker-compose.yaml

Et le docker marchait, et nous voyons passer les requètes dans les logs dockers.

![image](https://github.com/Cimmerian-Iter/Covid-DB_Fullstack/assets/37327589/3443d7db-297e-4055-a798-e6cba5c092cd)


